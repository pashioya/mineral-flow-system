package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;

import java.util.List;
import java.util.UUID;

public interface CreateOrderItemPort {
    void createOrderItems(List<PurchaseOrder.OrderItem> orderItems, UUID orderUUID);
}
