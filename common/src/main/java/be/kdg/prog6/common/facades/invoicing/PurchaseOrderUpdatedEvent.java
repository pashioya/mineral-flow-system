package be.kdg.prog6.common.facades.invoicing;

import be.kdg.prog6.common.domain.OrderStatus;

import java.util.List;
import java.util.UUID;

public record PurchaseOrderUpdatedEvent(UUID purchaseOrderUUID,
                                        String address,
                                        String deliveryDate,
                                        OrderStatus orderStatus,
                                        List<OrderItem> orderItems) implements PurchaseOrderEvent {
}
