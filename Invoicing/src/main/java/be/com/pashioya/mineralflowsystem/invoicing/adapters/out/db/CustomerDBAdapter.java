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
        customerRepository.save(new CustomerJPAEntity(customer));
    }
}
