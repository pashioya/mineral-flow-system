package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateFulfillmentOrderPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadActivePurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadInventoryItemPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.domain.OrderStatus;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCreateFulfillmentOrderUseCase implements CreateFulfillmentOrderUseCase {

    private final CreateFulfillmentOrderPort createFulfillmentOrderPort;
    private final LoadActivePurchaseOrderPort loadActivePurchaseOrderPort;
    private final LoadInventoryItemPort loadInventoryItemPort;
    private final LoadWarehousePort loadWarehousePort;
    private final Logger logger = LoggerFactory.getLogger(DefaultCreateFulfillmentOrderUseCase.class);

    @Override
    public void createFulfillmentOrder(CreateFulfillmentOrderCommand command) {
        ActivePurchaseOrder activePurchaseOrder = loadActivePurchaseOrder(command.purchaseOrderUUID());
        List<InventoryItem> inventoryItems = loadInventoryItemPort.loadByCustomerUUID(activePurchaseOrder.getWarehouseCustomerUUID());
        List<Warehouse> warehouses = loadWarehousePort.loadByWarehouseCustomerUUID(activePurchaseOrder.getWarehouseCustomerUUID());

        FulfillmentOrder fulfillmentOrder = initializeFulfillmentOrder(activePurchaseOrder);
        fulfillmentOrder.setOrderItems(createFulfillmentOrderItems(activePurchaseOrder, warehouses, inventoryItems));

        createFulfillmentOrderPort.createFulfillmentOrder(fulfillmentOrder);
    }

    private ActivePurchaseOrder loadActivePurchaseOrder(UUID purchaseOrderUUID) {
        return loadActivePurchaseOrderPort.loadActivePurchaseOrder(purchaseOrderUUID)
                .orElseThrow(() -> new RuntimeException("Active purchase order not found"));
    }

    private FulfillmentOrder initializeFulfillmentOrder(ActivePurchaseOrder activePurchaseOrder) {
        FulfillmentOrder fulfillmentOrder = new FulfillmentOrder();
        fulfillmentOrder.setFulfillmentOrderUUID(new FulfillmentOrder.FulfillmentOrderUUID(UUID.randomUUID()));
        fulfillmentOrder.setDateOrdered(LocalDateTime.now());
        fulfillmentOrder.setOrderStatus(OrderStatus.CREATED);
        fulfillmentOrder.setExpectedDeliveryDate(LocalDateTime.now().plusDays(5));
        fulfillmentOrder.setWarehouseCustomerUUID(activePurchaseOrder.getWarehouseCustomerUUID());
        fulfillmentOrder.setPurchaseOrderUUID(activePurchaseOrder.getPurchaseOrderUUID().uuid());
        return fulfillmentOrder;
    }

    private List<FulfillmentOrder.FulfillmentOrderItem> createFulfillmentOrderItems(
            ActivePurchaseOrder activePurchaseOrder, List<Warehouse> warehouses, List<InventoryItem> inventoryItems) {

        return activePurchaseOrder.getPurchaseOrderItems().stream()
                .map(purchaseOrderItem -> findOrCreateFulfillmentOrderItem(purchaseOrderItem, warehouses, inventoryItems, activePurchaseOrder))
                .toList();
    }

    private FulfillmentOrder.FulfillmentOrderItem findOrCreateFulfillmentOrderItem(
            ActivePurchaseOrder.PurchaseOrderItem purchaseOrderItem,
            List<Warehouse> warehouses, List<InventoryItem> inventoryItems, ActivePurchaseOrder activePurchaseOrder) {

        Optional<Warehouse> matchingWarehouse = findMatchingWarehouse(warehouses, inventoryItems, purchaseOrderItem);
        logger.info("Warehouse with matching material UUID: {}", matchingWarehouse);

        UUID warehouseUUID = matchingWarehouse
                .map(Warehouse::getWarehouseUUID)
                .orElseGet(() -> findWarehouseForMaterial(activePurchaseOrder, purchaseOrderItem, warehouses)).uuid();

        return new FulfillmentOrder.FulfillmentOrderItem(
                purchaseOrderItem.getMaterialUUID(),
                purchaseOrderItem.getQuantity(),
                purchaseOrderItem.getPrice(),
                UUID.randomUUID(),
                warehouseUUID
        );
    }

    private Optional<Warehouse> findMatchingWarehouse(
            List<Warehouse> warehouses, List<InventoryItem> inventoryItems, ActivePurchaseOrder.PurchaseOrderItem purchaseOrderItem) {

        return warehouses.stream()
                .filter(warehouse -> inventoryItems.stream()
                        .anyMatch(inventoryItem ->
                                inventoryItem.getMaterialUUID().uuid().equals(purchaseOrderItem.getMaterialUUID()) &&
                                        inventoryItem.getWareHouseUUID().equals(warehouse.getWarehouseUUID())))
                .findFirst();
    }

    private Warehouse.WareHouseUUID findWarehouseForMaterial(ActivePurchaseOrder activePurchaseOrder, ActivePurchaseOrder.PurchaseOrderItem purchaseOrderItem, List<Warehouse> warehouses) {
        return warehouses.stream()
                .filter(warehouse -> warehouse.getWarehouseCustomerUUID().equals(activePurchaseOrder.getWarehouseCustomerUUID()) &&
                        warehouse.getMaterialUUID().uuid().equals(purchaseOrderItem.getMaterialUUID()))
                .findFirst()
                .map(Warehouse::getWarehouseUUID)
                .orElseThrow(() -> new RuntimeException("No available warehouse found for material: " + purchaseOrderItem.getMaterialUUID()));
    }
}
