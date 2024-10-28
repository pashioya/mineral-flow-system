package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;

import java.util.List;

public interface LoadFulfillmentOrderPort {
    List<FulfillmentOrder> loadAllFulfillmentOrders();
}
