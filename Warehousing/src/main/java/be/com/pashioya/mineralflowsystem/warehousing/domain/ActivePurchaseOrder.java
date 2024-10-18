package be.com.pashioya.mineralflowsystem.warehousing.domain;

import be.kdg.prog6.common.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActivePurchaseOrder {
    private PurchaseOrderUUID purchaseOrderUUID;
    private WarehouseCustomer.WarehouseCustomerUUID warehouseCustomerUUID;
    private String orderNumber;
    private LocalDateTime deliveryDate;
    private LocalDateTime dateReceived;
    private String address;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;

    public record PurchaseOrderUUID(UUID uuid) {
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class OrderItem {
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
}
