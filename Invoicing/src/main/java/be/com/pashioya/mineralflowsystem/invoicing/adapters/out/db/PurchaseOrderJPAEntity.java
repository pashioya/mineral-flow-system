package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

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
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJPAEntity> orderItems;
}
