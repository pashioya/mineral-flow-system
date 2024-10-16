package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;

public interface CreateInventoryItemPort {
    void inventoryItemCreated(InventoryItem inventoryItem);
}
