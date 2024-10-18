package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWareHouseCustomerPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadCustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CustomerProjectionDBAdapter implements LoadCustomerPort, CreateWareHouseCustomerPort {

    private final CustomerProjectionRepository customerProjectionRepository;
    @Override
    public Optional<WarehouseCustomer> loadCustomer(UUID customerUUID) {
        return Optional.of(new WarehouseCustomer(customerProjectionRepository.findById(customerUUID).orElseThrow()));
    }

    @Override
    public List<WarehouseCustomer> loadCustomers() {
        return customerProjectionRepository.findAll().stream().map(WarehouseCustomer::new).toList();
    }

    @Override
    public void createWareHouseCustomer(WarehouseCustomer warehouseCustomer) {
        CustomerProjectionJPAEntity warehouseCustomerJPAEntity = new CustomerProjectionJPAEntity(warehouseCustomer);
        customerProjectionRepository.save(warehouseCustomerJPAEntity);
    }
}
