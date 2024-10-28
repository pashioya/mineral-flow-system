package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.kdg.prog6.common.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UpdatePurchaseOrderCommand(UUID purchaseOrderUUID,
                                         String address,
                                         LocalDateTime deliveryDate,
                                         OrderStatus orderStatus,
                                         List<ActivePurchaseOrder.PurchaseOrderItem> purchaseOrderItems)
                                          {
}
