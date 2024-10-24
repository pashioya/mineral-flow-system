package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private UUID purchaseOrderUUID;
    private UUID orderItemUUID;
    private UUID materialUUID;
    private int quantity;
    private double price;

    public OrderItemDTO(ActivePurchaseOrder.OrderItem orderItem) {
        this.orderItemUUID = orderItem.getOrderItemUUID();
        this.purchaseOrderUUID = orderItem.getPurchaseOrderUUID();
        this.materialUUID = orderItem.getMaterialUUID();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
