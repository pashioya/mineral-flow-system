package be.com.pashioya.mineralflowsystem.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PurchaseOrder {
    private PurchaseOrderUUID purchaseOrderUUID;
    private Customer.CustomerUUID customerUUID;
    private String orderNumber;
    private String orderDate;
    private List<OrderItem> orderItems;

    public record PurchaseOrderUUID(UUID uuid) {
    }
}
