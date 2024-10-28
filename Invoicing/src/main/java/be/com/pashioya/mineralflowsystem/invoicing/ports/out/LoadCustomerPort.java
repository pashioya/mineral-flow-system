package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface LoadCustomerPort {
    Optional<Customer> loadCustomer(Customer.CustomerUUID customerUUID);
    List<Customer> loadAllCustomers();
}
