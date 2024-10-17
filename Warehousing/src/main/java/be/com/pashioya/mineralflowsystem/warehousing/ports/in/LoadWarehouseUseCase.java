package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadWarehouseUseCase {
    Optional<Warehouse> loadWarehouse(UUID warehouseUUID);

    List<Warehouse> loadAllWarehouses();
}
