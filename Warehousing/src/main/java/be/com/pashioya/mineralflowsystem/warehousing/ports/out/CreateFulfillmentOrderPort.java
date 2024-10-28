package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;

public interface CreateFulfillmentOrderPort {
    void createFulfillmentOrder(FulfillmentOrder fulfillmentOrder);
}
