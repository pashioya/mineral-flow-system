package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.OrderItem;

import java.util.List;
import java.util.UUID;

public interface CreateOrderItemPort {
    void createOrderItems(List<OrderItem> orderItems, UUID orderUUID);
}
