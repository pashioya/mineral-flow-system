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

    public OrderItemDTO(ActivePurchaseOrder.PurchaseOrderItem purchaseOrderItem) {
        this.orderItemUUID = purchaseOrderItem.getOrderItemUUID();
        this.purchaseOrderUUID = purchaseOrderItem.getPurchaseOrderUUID();
        this.materialUUID = purchaseOrderItem.getMaterialUUID();
        this.quantity = purchaseOrderItem.getQuantity();
        this.price = purchaseOrderItem.getPrice();
    }
}
