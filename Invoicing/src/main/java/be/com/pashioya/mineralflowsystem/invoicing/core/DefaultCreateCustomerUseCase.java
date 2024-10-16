package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateCustomerPort;
import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCreateCustomerUseCase implements CreateCustomerUseCase {
    private final CreateCustomerPort createCustomerPort;

    @Override
    public void createCustomer(CreateCustomerCommand command) {
        Customer customer = new Customer(new Customer.CustomerUUID(UUID.randomUUID()),command.name(), command.address(), command.email(), command.vatNumber());
        createCustomerPort.createCustomer(customer);
    }
}
