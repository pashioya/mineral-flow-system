package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;

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

    private String orderDate;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJPAEntity> orderItems;
}
