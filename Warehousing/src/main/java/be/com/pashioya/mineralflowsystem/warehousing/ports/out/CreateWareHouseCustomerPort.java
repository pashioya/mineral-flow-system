package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;

public interface CreateWareHouseCustomerPort {
    void createWareHouseCustomer(WarehouseCustomer warehouseCustomer);
}
