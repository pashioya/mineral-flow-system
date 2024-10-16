package be.com.pashioya.mineralflowsystem.warehousing.ports.out;



import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadCustomerPort {
    Optional<WarehouseCustomer> loadCustomer(UUID customerUUID);
    List<WarehouseCustomer> loadCustomers();
}
