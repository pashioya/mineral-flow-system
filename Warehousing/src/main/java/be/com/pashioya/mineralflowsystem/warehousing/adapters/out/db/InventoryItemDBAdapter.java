package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateInventoryItemPort;

import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadInventoryItemPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class InventoryItemDBAdapter implements CreateInventoryItemPort, LoadInventoryItemPort {

    private final InventoryItemRepository inventoryItemRepository;

    @Override
    public void createInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(new InventoryItemJPAEntity(inventoryItem));
    }

    @Override
    public List<InventoryItem> loadAllInventoryItems() {
        return inventoryItemRepository.findAll().stream().map(InventoryItem::new).toList();
    }

    @Override
    public List<InventoryItem> loadByCustomerUUID(WarehouseCustomer.WarehouseCustomerUUID customerUUID) {
        return inventoryItemRepository.findByCustomerUUID(customerUUID.uuid()).stream().map(InventoryItem::new).toList();
    }
}
