package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateInventoryItemPort;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class InventoryItemDBAdapter implements CreateInventoryItemPort {

    private final InventoryItemRepository inventoryItemRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public void createInventoryItem(InventoryItem inventoryItem) {
        InventoryItemJPAEntity inventoryItemJPAEntity = getInventoryItemJPAEntity(inventoryItem);

        warehouseRepository.findById(inventoryItem.getWarehouse().getWarehouseUUID().uuid())
                .ifPresent(warehouseJPAEntity -> {
                    warehouseJPAEntity.setMaterialUUID(inventoryItem.getMaterial().getUuid().uuid());
                    warehouseJPAEntity.setCustomerUUID(inventoryItem.getCustomer().getWarehouseCustomerUUID().uuid());
                    warehouseJPAEntity.setCapacity(warehouseJPAEntity.getCapacity() + inventoryItem.getQuantity());
                    warehouseRepository.save(warehouseJPAEntity);
                });

        inventoryItemRepository.save(inventoryItemJPAEntity);
    }

    private static InventoryItemJPAEntity getInventoryItemJPAEntity(InventoryItem inventoryItem) {
        InventoryItemJPAEntity inventoryItemJPAEntity = new InventoryItemJPAEntity();
        inventoryItemJPAEntity.setInventoryItemUUID(inventoryItem.getUuid().uuid());
        inventoryItemJPAEntity.setCustomerUUID(inventoryItem.getCustomer().getWarehouseCustomerUUID().uuid());
        inventoryItemJPAEntity.setMaterialUUID(inventoryItem.getMaterial().getUuid().uuid());
        inventoryItemJPAEntity.setWarehouseUUID(inventoryItem.getWarehouse().getWarehouseUUID().uuid());
        inventoryItemJPAEntity.setQuantity(inventoryItem.getQuantity());
        inventoryItemJPAEntity.setDateReceived(inventoryItem.getDateReceived());
        return inventoryItemJPAEntity;
    }
}
