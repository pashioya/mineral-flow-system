package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateFulfillmentOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultCreateFulfillmentOrderUseCase implements CreateFulfillmentOrderUseCase {

    private final CreateFulfillmentOrderPort createFulfillmentOrderPort;

    @Override
    public void createFulfillmentOrder(CreateFulfillmentOrderCommand command) {
        createFulfillmentOrderPort.createFulfillmentOrder(
                new FulfillmentOrder(command)
        );
    }
}
