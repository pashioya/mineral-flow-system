package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateInventoryItemCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateInventoryItemUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateInventoryItemPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadCustomerPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadMaterialPort;
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

    @Override
    public void createInventoryItem(CreateInventoryItemCommand command) {

        Material material = loadMaterialPort.loadMaterial(command.materialUUID()).orElseThrow();
        WarehouseCustomer customer = loadCustomerPort.loadCustomer(command.customerUUID()).orElseThrow();

        InventoryItem inventoryItem = new InventoryItem(new InventoryItem.InventoryItemUUID(UUID.randomUUID()), customer, material, command.quantity(), command.dateReceived());
        createInventoryItemPort.inventoryItemCreated(inventoryItem);
    }
}
