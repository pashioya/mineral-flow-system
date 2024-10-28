package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.kdg.prog6.common.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Entity
@Table(name = "active_purchase_orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivePurchaseOrderJPAEntity {
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

    public ActivePurchaseOrderJPAEntity(ActivePurchaseOrder activePurchaseOrder){

        this.purchaseOrderUUID = activePurchaseOrder.getPurchaseOrderUUID().uuid();
        this.customerUUID = activePurchaseOrder.getWarehouseCustomerUUID().uuid();
        this.orderNumber = activePurchaseOrder.getOrderNumber();
        this.deliveryDate = activePurchaseOrder.getDeliveryDate();
        this.dateReceived = activePurchaseOrder.getDateReceived();
        this.address = activePurchaseOrder.getAddress();
        this.orderStatus = activePurchaseOrder.getOrderStatus();
        this.orderItems = activePurchaseOrder.getPurchaseOrderItems().stream().map(
                orderItem -> new OrderItemJPAEntity(orderItem, this)
        ).toList();

    }
}
