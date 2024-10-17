package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;

public interface UpdateWarehousePort {
    void updateWarehouse(Warehouse warehouse);
}
