package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateInventoryItemCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateInventoryItemUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.*;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCreateInventoryItemUseCase implements CreateInventoryItemUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCreateInventoryItemUseCase.class);

    private final CreateInventoryItemPort createInventoryItemPort;
    private final LoadMaterialPort loadMaterialPort;
    private final LoadCustomerPort loadCustomerPort;
    private final LoadWarehousePort loadWarehousePort;
    private final UpdateWarehousePort updateWarehousePort;

    @Override
    public void createInventoryItem(CreateInventoryItemCommand command) {
        try {
            WarehouseCustomer customer = loadCustomerPort.loadCustomer(command.customerUUID())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            Warehouse warehouse = loadWarehousePort.loadWarehouse(command.warehouseUUID())
                    .orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));

            if (warehouse.getWarehouseCustomerUUID() != null &&
                !warehouse.getWarehouseCustomerUUID().uuid().equals(command.customerUUID())) {
                logger.error("Warehouse already has a different customer: {}", warehouse.getWarehouseCustomerUUID().uuid());
                return;  // Optionally return or handle this error case gracefully.
            }

            if (warehouse.getMaterialUUID() != null &&
                !warehouse.getMaterialUUID().uuid().equals(command.materialUUID())) {
                logger.error("Warehouse already has a different material: {}", warehouse.getMaterialUUID().uuid());
                return;  // Optionally return or handle this error case gracefully.
            }

            Material material = loadMaterialPort.loadMaterial(command.materialUUID())
                    .orElseThrow(() -> new IllegalArgumentException("Material not found"));

            InventoryItem inventoryItem = new InventoryItem(
                    new InventoryItem.InventoryItemUUID(UUID.randomUUID()),
                    customer.getWarehouseCustomerUUID(),
                    material.getMaterialUUID(),
                    warehouse.getWarehouseUUID(),
                    command.quantity(),
                    command.dateReceived()
            );
            createInventoryItemPort.createInventoryItem(inventoryItem);

            // Update warehouse data
            warehouse.setWarehouseCustomerUUID(customer.getWarehouseCustomerUUID());
            warehouse.setMaterialUUID(material.getMaterialUUID());
            warehouse.setCapacity(warehouse.getCapacity() + command.quantity());
            updateWarehousePort.updateWarehouse(warehouse);

        } catch (IllegalArgumentException e) {
            logger.error("Error creating inventory item: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }
}
