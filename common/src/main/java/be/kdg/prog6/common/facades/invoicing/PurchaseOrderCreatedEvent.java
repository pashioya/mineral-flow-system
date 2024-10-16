package be.kdg.prog6.common.facades.invoicing;

import java.util.List;
import java.util.UUID;

public record PurchaseOrderCreatedEvent(
     UUID purchaseOrderUUID,
     UUID customerUUID,
     String orderNumber,
     String orderDate,
         List<OrderItem>orderItems
) implements PurchaseOrderEvent{
    public record OrderItem(
            UUID orderItemUUID,
            UUID purchaseOrderUUID,
            UUID materialUUID,
            int quantity,
            double price
    ) {
    }

}