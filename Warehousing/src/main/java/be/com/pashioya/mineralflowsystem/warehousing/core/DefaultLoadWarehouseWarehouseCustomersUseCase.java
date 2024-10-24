package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadWarehouseCustomerUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadCustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultLoadWarehouseWarehouseCustomersUseCase implements LoadWarehouseCustomerUseCase {
    LoadCustomerPort loadCustomerPort;

    @Override
    public Optional<WarehouseCustomer> loadWarehouseCustomer(UUID customerUUID) {
        return loadCustomerPort.loadCustomer(customerUUID);
    }

    @Override
    public List<WarehouseCustomer> loadWarehouseCustomers() {
        return loadCustomerPort.loadCustomers();
    }
}
