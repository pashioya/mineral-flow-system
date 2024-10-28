package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import be.kdg.prog6.common.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "fulfillment_orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FulfillmentOrderJPAEntity {
    @Id
    private UUID fulfillmentOrderUUID;
    private UUID purchaseOrderUUID;
    private UUID customerUUID;
    private String orderNumber;
    private LocalDateTime dateOrdered;
    private LocalDateTime expectedDeliveryDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "fulfillmentOrder",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FulfillmentOrderItemJPAEntity> orderItems;

    public FulfillmentOrderJPAEntity(FulfillmentOrder fulfillmentOrder){
        this.fulfillmentOrderUUID = fulfillmentOrder.getFulfillmentOrderUUID().uuid();
        this.purchaseOrderUUID = fulfillmentOrder.getPurchaseOrderUUID();
        this.dateOrdered = fulfillmentOrder.getDateOrdered();
        this.expectedDeliveryDate = fulfillmentOrder.getExpectedDeliveryDate();
        this.customerUUID = fulfillmentOrder.getWarehouseCustomerUUID().uuid();
        this.orderNumber = fulfillmentOrder.getOrderNumber();
        this.orderStatus = fulfillmentOrder.getOrderStatus();
        this.orderItems = fulfillmentOrder.getOrderItems().stream().map(
                fulfillmentOrderItem -> new FulfillmentOrderItemJPAEntity(fulfillmentOrderItem, this)
        ).toList();

    }
}
