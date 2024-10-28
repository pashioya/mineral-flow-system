package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateCustomerPort;
import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.LoadCustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CustomerDBAdapter implements CreateCustomerPort, LoadCustomerPort {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(new CustomerJPAEntity(customer));
    }

    @Override
    public Optional<Customer> loadCustomer(Customer.CustomerUUID customerUUID) {
        return customerRepository.findById(customerUUID.uuid()).map(Customer::new);
    }

    @Override
    public List<Customer> loadAllCustomers() {
        return customerRepository.findAll().stream().map(Customer::new).toList();
    }
}
