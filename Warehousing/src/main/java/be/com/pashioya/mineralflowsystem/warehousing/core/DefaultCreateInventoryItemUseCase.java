package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateInventoryItemCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateInventoryItemUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.*;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCreateInventoryItemUseCase implements CreateInventoryItemUseCase {

    private final CreateInventoryItemPort createInventoryItemPort;
    private final LoadMaterialPort loadMaterialPort;
    private final LoadCustomerPort loadCustomerPort;
    private final LoadWarehousePort loadWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;

    @Override
    public void createInventoryItem(CreateInventoryItemCommand command) {

        WarehouseCustomer customer = loadCustomerPort.loadCustomer(command.customerUUID()).orElseThrow();
        Warehouse warehouse = loadWarehousePort.loadWarehouse(command.warehouseUUID()).orElseThrow();

        if (warehouse.getCustomer() != null && !warehouse.getCustomer().getWarehouseCustomerUUID().uuid().equals(command.customerUUID())) {
            throw new IllegalStateException("Warehouse already has a different customer");
        }
        if (warehouse.getMaterial() != null && !warehouse.getMaterial().getUuid().uuid().equals(command.materialUUID())) {
            throw new IllegalStateException("Warehouse already has a different material");
        }

        Material material = loadMaterialPort.loadMaterial(command.materialUUID()).orElseThrow();

        InventoryItem inventoryItem = new InventoryItem(
                new InventoryItem.InventoryItemUUID(
                        UUID.randomUUID()),
                customer,
                material,
                warehouse,
                command.quantity(),
                command.dateReceived()
        );
        createInventoryItemPort.createInventoryItem(inventoryItem);

        warehouse.setCustomer(customer);
        warehouse.setMaterial(material);

        warehouse.setCapacity(warehouse.getCapacity() + command.quantity());
        updateWarehousePort.updateWarehouse(warehouse);
    }
}
