package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWareHouseCustomerPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadCustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerProjectionDBAdapter implements LoadCustomerPort, CreateWareHouseCustomerPort {

    private final CustomerProjectionRepository customerProjectionRepository;
    @Override
    public Optional<WarehouseCustomer> loadCustomer(UUID customerUUID) {
        return customerProjectionRepository.findById(customerUUID).map(customerProjectionJPAEntity -> new WarehouseCustomer(
                new WarehouseCustomer.WarehouseCustomerUUID(customerProjectionJPAEntity.getCustomerUUID()),
                customerProjectionJPAEntity.getName(),
                customerProjectionJPAEntity.getAddress(),
                customerProjectionJPAEntity.getEmail()
        ));
    }

    @Override
    public List<WarehouseCustomer> loadCustomers() {
        return customerProjectionRepository.findAll().stream().map(customerProjectionJPAEntity -> new WarehouseCustomer(
                new WarehouseCustomer.WarehouseCustomerUUID(customerProjectionJPAEntity.getCustomerUUID()),
                customerProjectionJPAEntity.getName(),
                customerProjectionJPAEntity.getAddress(),
                customerProjectionJPAEntity.getEmail()
        )).toList();
    }

    @Override
    public void createWareHouseCustomer(WarehouseCustomer warehouseCustomer) {

        CustomerProjectionJPAEntity warehouseCustomerJPAEntity = new CustomerProjectionJPAEntity();
        warehouseCustomerJPAEntity.setCustomerUUID(warehouseCustomer.getWarehouseCustomerUUID().uuid());
        warehouseCustomerJPAEntity.setName(warehouseCustomer.getName());
        warehouseCustomerJPAEntity.setAddress(warehouseCustomer.getAddress());
        warehouseCustomerJPAEntity.setEmail(warehouseCustomer.getEmail());
        customerProjectionRepository.save(warehouseCustomerJPAEntity);

    }
}
