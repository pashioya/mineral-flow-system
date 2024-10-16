package be.com.pashioya.mineralflowsystem.invoicing.ports.in;

import be.com.pashioya.mineralflowsystem.invoicing.domain.OrderItem;

import java.util.List;
import java.util.UUID;

public record CreatePurchaseOrderCommand(
        UUID customerUUID,
         String orderNumber,
         String orderDate,
         List<OrderItem>orderItems) {
}
