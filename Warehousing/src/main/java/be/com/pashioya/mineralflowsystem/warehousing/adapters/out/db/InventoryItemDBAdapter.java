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
    public void createInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(new InventoryItemJPAEntity(inventoryItem));
    }
}
