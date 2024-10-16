package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateCustomerPort;
import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class CustomerDBAdapter implements CreateCustomerPort {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(Customer customer) {
        CustomerJPAEntity customerJPAEntity = new CustomerJPAEntity();
        customerJPAEntity.setCustomerUUID(customer.getCustomerUUID().uuid());
        customerJPAEntity.setName(customer.getName());
        customerJPAEntity.setAddress(customer.getAddress());
        customerJPAEntity.setEmail(customer.getEmail());
        customerJPAEntity.setVatNumber(customer.getVatNumber());

        customerRepository.save(customerJPAEntity);
    }
}
