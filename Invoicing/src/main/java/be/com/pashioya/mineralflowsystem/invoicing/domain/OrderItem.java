package be.com.pashioya.mineralflowsystem.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItem {
    private UUID orderItemUUID;
    private UUID purchaseOrderUUID;
    private UUID materialUUID;
    private int quantity;
    private double price;

    public OrderItem(UUID materialUUID, int quantity, double price) {
        this.orderItemUUID = UUID.randomUUID();
        this.purchaseOrderUUID = null;
        this.materialUUID = materialUUID;
        this.quantity = quantity;
        this.price = price;
    }
}
