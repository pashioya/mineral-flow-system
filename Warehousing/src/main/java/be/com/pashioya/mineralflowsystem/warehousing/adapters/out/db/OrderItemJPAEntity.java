package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

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
    private ActivePurchaseOrderJPAEntity purchaseOrder;
}
