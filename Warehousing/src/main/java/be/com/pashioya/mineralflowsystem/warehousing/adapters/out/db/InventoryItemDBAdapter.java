package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateInventoryItemPort;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class InventoryItemDBAdapter implements CreateInventoryItemPort {

    private final InventoryItemRepository inventoryItemRepository;

    @Override
    public void inventoryItemCreated(InventoryItem inventoryItem) {
        InventoryItemJPAEntity inventoryItemJPAEntity = new InventoryItemJPAEntity();
        inventoryItemJPAEntity.setInventoryItemUUID(inventoryItem.getUuid().uuid());
        inventoryItemJPAEntity.setCustomerUUID(inventoryItem.getCustomer().getWarehouseCustomerUUID().uuid());
        inventoryItemJPAEntity.setMaterialUUID(inventoryItem.getMaterial().getUuid().uuid());
        inventoryItemJPAEntity.setQuantity(inventoryItem.getQuantity());
        inventoryItemJPAEntity.setDateReceived(inventoryItem.getDateReceived());

        inventoryItemRepository.save(inventoryItemJPAEntity);
    }
}
