package be.com.pashioya.mineralflowsystem.warehousing.domain;


import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.FulfillmentOrderItemJPAEntity;
import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.FulfillmentOrderJPAEntity;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderCommand;
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
public class FulfillmentOrder {
    private FulfillmentOrderUUID fulfillmentOrderUUID;
    private WarehouseCustomer.WarehouseCustomerUUID warehouseCustomerUUID;
    private UUID purchaseOrderUUID;
    private String orderNumber;
    private LocalDateTime dateOrdered;
    private LocalDateTime expectedDeliveryDate;
    private OrderStatus orderStatus;
    private List<FulfillmentOrderItem> orderItems;

    public FulfillmentOrder(FulfillmentOrderJPAEntity fulfillmentOrderJPAEntity) {
        this.fulfillmentOrderUUID = new FulfillmentOrderUUID(fulfillmentOrderJPAEntity.getFulfillmentOrderUUID());
        this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(fulfillmentOrderJPAEntity.getCustomerUUID());
        this.purchaseOrderUUID = fulfillmentOrderJPAEntity.getPurchaseOrderUUID();
        this.orderNumber = fulfillmentOrderJPAEntity.getOrderNumber();
        this.dateOrdered = fulfillmentOrderJPAEntity.getDateOrdered();
        this.expectedDeliveryDate = fulfillmentOrderJPAEntity.getExpectedDeliveryDate();
        this.orderStatus = fulfillmentOrderJPAEntity.getOrderStatus();
        this.orderItems = fulfillmentOrderJPAEntity.getOrderItems().stream().map(FulfillmentOrderItem::new).toList();
    }

    public record FulfillmentOrderUUID(UUID uuid) {
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class FulfillmentOrderItem {
        private UUID fulfillmentOrderItemUUID;
        private UUID fulfillmentOrderUUID;
        private UUID materialUUID;
        private UUID warehouseUUID;
        private int quantity;
        private double price;

        public FulfillmentOrderItem(UUID materialUUID, int quantity, double price, UUID fulfillmentOrderUUID, UUID warehouseUUID) {
            this.fulfillmentOrderItemUUID = UUID.randomUUID();
            this.fulfillmentOrderUUID = fulfillmentOrderUUID;
            this.materialUUID = materialUUID;
            this.quantity = quantity;
            this.price = price;
            this.warehouseUUID = warehouseUUID;
        }

        public FulfillmentOrderItem(FulfillmentOrderItemJPAEntity fulfillmentOrderItemJPAEntity) {
            this.fulfillmentOrderItemUUID = fulfillmentOrderItemJPAEntity.getFulfillmentOrderItemUUID();
            this.fulfillmentOrderUUID = fulfillmentOrderItemJPAEntity.getFulfillmentOrder().getFulfillmentOrderUUID();
            this.materialUUID = fulfillmentOrderItemJPAEntity.getMaterialUUID();
            this.quantity = fulfillmentOrderItemJPAEntity.getQuantity();
            this.price = fulfillmentOrderItemJPAEntity.getPrice();
            this.warehouseUUID = fulfillmentOrderItemJPAEntity.getWarehouseUUID();
        }
    }
}
