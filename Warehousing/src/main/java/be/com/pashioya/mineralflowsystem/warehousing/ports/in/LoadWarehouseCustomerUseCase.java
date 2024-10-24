package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadWarehouseCustomerUseCase {

    Optional<WarehouseCustomer> loadWarehouseCustomer(UUID customerUUID);
    List<WarehouseCustomer> loadWarehouseCustomers();
}
