package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;


import java.util.List;
import java.util.UUID;

public record CreateFulfillmentOrderCommand(
        UUID purchaseOrderUUID,
        UUID customerUUID,
        UUID warehouseUUID,
        String orderNumber,
        List<FulfillmentOrder.FulfillmentOrderItem> orderItems
) {

}
