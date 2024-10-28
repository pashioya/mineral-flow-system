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
    private UUID warehouseUUID;
    private UUID purchaseOrderUUID;
    private String orderNumber;
    private LocalDateTime dateOrdered;
    private LocalDateTime expectedDeliveryDate;
    private OrderStatus orderStatus;
    private List<FulfillmentOrderItem> orderItems;

    public FulfillmentOrder(FulfillmentOrderJPAEntity fulfillmentOrderJPAEntity) {
        this.fulfillmentOrderUUID = new FulfillmentOrderUUID(fulfillmentOrderJPAEntity.getFulfillmentOrderUUID());
        this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(fulfillmentOrderJPAEntity.getCustomerUUID());
        this.warehouseUUID = fulfillmentOrderJPAEntity.getWarehouseUUID();
        this.purchaseOrderUUID = fulfillmentOrderJPAEntity.getPurchaseOrderUUID();
        this.orderNumber = fulfillmentOrderJPAEntity.getOrderNumber();
        this.dateOrdered = fulfillmentOrderJPAEntity.getDateOrdered();
        this.expectedDeliveryDate = fulfillmentOrderJPAEntity.getExpectedDeliveryDate();
        this.orderStatus = fulfillmentOrderJPAEntity.getOrderStatus();
        this.orderItems = fulfillmentOrderJPAEntity.getOrderItems().stream().map(FulfillmentOrderItem::new).toList();
    }

    public record FulfillmentOrderUUID(UUID uuid) {
    }

    public FulfillmentOrder(CreateFulfillmentOrderCommand command){
        this.fulfillmentOrderUUID = new FulfillmentOrderUUID(UUID.randomUUID());
        this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(command.customerUUID());
        this.warehouseUUID = command.warehouseUUID();
        this.purchaseOrderUUID = command.purchaseOrderUUID();
        this.orderNumber = command.orderNumber();
        this.dateOrdered = LocalDateTime.now();
        this.expectedDeliveryDate = LocalDateTime.now().plusDays(5);
        this.orderStatus = OrderStatus.CREATED;
        this.orderItems = command.orderItems().stream().map(
                orderItem -> new FulfillmentOrderItem(
                        orderItem.getMaterialUUID(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        this.fulfillmentOrderUUID.uuid)).toList();
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class FulfillmentOrderItem {
        private UUID fulfillmentOrderItemUUID;
        private UUID fulfillmentOrderUUID;
        private UUID materialUUID;
        private int quantity;
        private double price;

        public FulfillmentOrderItem(UUID materialUUID, int quantity, double price, UUID fulfillmentOrderUUID) {
            this.fulfillmentOrderItemUUID = UUID.randomUUID();
            this.fulfillmentOrderUUID = fulfillmentOrderUUID;
            this.materialUUID = materialUUID;
            this.quantity = quantity;
            this.price = price;
        }

        public FulfillmentOrderItem(FulfillmentOrderItemJPAEntity fulfillmentOrderItemJPAEntity) {
            this.fulfillmentOrderItemUUID = fulfillmentOrderItemJPAEntity.getFulfillmentOrderItemUUID();
            this.fulfillmentOrderUUID = fulfillmentOrderItemJPAEntity.getFulfillmentOrder().getFulfillmentOrderUUID();
            this.materialUUID = fulfillmentOrderItemJPAEntity.getMaterialUUID();
            this.quantity = fulfillmentOrderItemJPAEntity.getQuantity();
            this.price = fulfillmentOrderItemJPAEntity.getPrice();
        }
    }
}
