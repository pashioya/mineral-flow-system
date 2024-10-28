package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;

import java.util.List;
import java.util.Optional;

public interface LoadInventoryItemPort {
    List<InventoryItem> loadAllInventoryItems();

    List<InventoryItem> loadByCustomerUUID(WarehouseCustomer.WarehouseCustomerUUID customerUUID);
}
