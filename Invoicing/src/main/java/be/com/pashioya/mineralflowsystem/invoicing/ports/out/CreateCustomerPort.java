package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;

public interface CreateCustomerPort {

    void createCustomer(Customer customer);
}
