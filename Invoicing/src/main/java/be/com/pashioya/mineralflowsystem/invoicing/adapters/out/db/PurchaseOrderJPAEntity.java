package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.kdg.prog6.common.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Entity
@Table(name = "purchase_orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrderJPAEntity {
    @Id
    private UUID purchaseOrderUUID;
    private UUID customerUUID;
    private String orderNumber;
    private LocalDateTime deliveryDate;
    private LocalDateTime dateReceived;
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "purchaseOrder",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJPAEntity> orderItems;

    public PurchaseOrderJPAEntity(PurchaseOrder purchaseOrder) {
        this.purchaseOrderUUID = purchaseOrder.getPurchaseOrderUUID().uuid();
        this.customerUUID = purchaseOrder.getCustomerUUID().uuid();
        this.orderNumber = purchaseOrder.getOrderNumber();
        this.address = purchaseOrder.getAddress();
        this.orderStatus = purchaseOrder.getOrderStatus();
        this.dateReceived = LocalDateTime.now();
        this.deliveryDate = purchaseOrder.getDeliveryDate();
        this.orderItems = purchaseOrder.getOrderItems().stream().map(
                orderItem -> new OrderItemJPAEntity(orderItem, this)
        ).toList();
    }
}
