package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadWarehousePort {
    Optional<Warehouse> loadWarehouse(UUID warehouseUUID);
    List<Warehouse> loadAllWarehouses();
    List<Warehouse> loadByWarehouseCustomerUUID(WarehouseCustomer.WarehouseCustomerUUID warehouseCustomerUUID);
}
