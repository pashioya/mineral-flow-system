package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemJPAEntity {
    @Id
    private UUID orderItemUUID;
    private UUID materialUUID;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "purchase_orderuuid")
    private PurchaseOrderJPAEntity purchaseOrder;

    public OrderItemJPAEntity(PurchaseOrder.OrderItem orderItem, PurchaseOrderJPAEntity purchaseOrderJPAEntity) {
        this.orderItemUUID = orderItem.getOrderItemUUID();
        this.materialUUID = orderItem.getMaterialUUID();
        this.quantity = orderItem.getQuantity();
        this.purchaseOrder = purchaseOrderJPAEntity;
        this.price = orderItem.getPrice();
    }
}
