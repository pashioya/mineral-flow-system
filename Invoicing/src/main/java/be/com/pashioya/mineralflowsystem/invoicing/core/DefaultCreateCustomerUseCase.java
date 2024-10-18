package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateCustomerPort;
import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultCreateCustomerUseCase implements CreateCustomerUseCase {
    private final List<CreateCustomerPort> createCustomerPort;

    @Override
    public void createCustomer(CreateCustomerCommand command) {
        createCustomerPort.forEach(port -> port.createCustomer(new Customer(command)));
    }
}
