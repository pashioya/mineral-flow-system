package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

public interface CreateFulfillmentOrderUseCase {

    void createFulfillmentOrder(CreateFulfillmentOrderCommand command);
}
