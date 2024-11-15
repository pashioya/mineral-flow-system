package be.com.pashioya.mineralflowsystem.invoicing.ports.in;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreatePurchaseOrderCommand(
        UUID customerUUID,
        String address,
        LocalDateTime deliveryDate,
        List<PurchaseOrder.OrderItem>orderItems) {
}
