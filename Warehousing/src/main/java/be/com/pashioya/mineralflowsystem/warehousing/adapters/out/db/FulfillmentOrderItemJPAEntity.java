package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "fulfillment_order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FulfillmentOrderItemJPAEntity {
    @Id
    private UUID fulfillmentOrderItemUUID;
    private UUID materialUUID;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "fulfillment_orderuuid")
    private FulfillmentOrderJPAEntity fulfillmentOrder;


    public FulfillmentOrderItemJPAEntity(FulfillmentOrder.FulfillmentOrderItem fulfillmentOrderItem, FulfillmentOrderJPAEntity fulfillmentOrderJPAEntity) {
        this.fulfillmentOrderItemUUID = fulfillmentOrderItem.getFulfillmentOrderItemUUID();
        this.materialUUID = fulfillmentOrderItem.getMaterialUUID();
        this.quantity = fulfillmentOrderItem.getQuantity();
        this.price = fulfillmentOrderItem.getPrice();
        this.fulfillmentOrder = fulfillmentOrderJPAEntity;
    }
}
