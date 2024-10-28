package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;

import java.util.List;

public interface LoadFulfillmentOrderUseCase {
    List<FulfillmentOrder> loadAllFulfillmentOrders();
}
