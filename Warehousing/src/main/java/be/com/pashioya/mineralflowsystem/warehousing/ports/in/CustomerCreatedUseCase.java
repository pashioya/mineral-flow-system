package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;

public interface CustomerCreatedUseCase {
    void createWarehouseCustomer(CustomerCreatedEvent customerEvent);
}
