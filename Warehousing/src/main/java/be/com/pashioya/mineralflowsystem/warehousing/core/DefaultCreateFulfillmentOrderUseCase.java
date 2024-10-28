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

@Override
public void createFulfillmentOrder(CreateFulfillmentOrderCommand command) {

    Optional<ActivePurchaseOrder> activePurchaseOrder = loadActivePurchaseOrderPort.loadActivePurchaseOrder(command.purchaseOrderUUID());

    if (activePurchaseOrder.isEmpty()) {
        throw new RuntimeException("Active purchase order not found");
    }

    List<InventoryItem> inventoryItems = loadInventoryItemPort.loadByCustomerUUID(activePurchaseOrder.get().getWarehouseCustomerUUID());
    List<Warehouse> warehouses = loadWarehousePort.loadByWarehouseCustomerUUID(activePurchaseOrder.get().getWarehouseCustomerUUID());

    FulfillmentOrder fulfillmentOrder = new FulfillmentOrder();

    fulfillmentOrder.setDateOrdered(LocalDateTime.now());
    fulfillmentOrder.setOrderStatus(OrderStatus.CREATED);
    fulfillmentOrder.setExpectedDeliveryDate(LocalDateTime.now().plusDays(5));
    fulfillmentOrder.setWarehouseCustomerUUID(activePurchaseOrder.get().getWarehouseCustomerUUID());

    fulfillmentOrder.setOrderItems(
        activePurchaseOrder.get().getPurchaseOrderItems().stream().map(purchaseOrderItem -> {
            // Find the warehouse that has the matching material UUID
            Optional<Warehouse> matchingWarehouse = warehouses.stream()
                .filter(warehouse -> inventoryItems.stream()
                    .anyMatch(inventoryItem ->
                        inventoryItem.getMaterialUUID().uuid().equals(purchaseOrderItem.getMaterialUUID())
                        && inventoryItem.getWareHouseUUID().equals(warehouse.getWarehouseUUID())
                    )
                )
                .findFirst();

            UUID warehouseUUID = matchingWarehouse
                .map(Warehouse::getWarehouseUUID)
                .orElseThrow(() -> new RuntimeException("No warehouse found with matching material UUID")).uuid();

            return new FulfillmentOrder.FulfillmentOrderItem(
                purchaseOrderItem.getMaterialUUID(),
                purchaseOrderItem.getQuantity(),
                purchaseOrderItem.getPrice(),
                fulfillmentOrder.getFulfillmentOrderUUID().uuid(),
                warehouseUUID
            );
        }).toList()
    );

        createFulfillmentOrderPort.createFulfillmentOrder(fulfillmentOrder);

}
}
