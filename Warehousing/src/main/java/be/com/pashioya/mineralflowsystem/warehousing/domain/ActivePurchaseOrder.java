package be.com.pashioya.mineralflowsystem.warehousing.domain;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.ActivePurchaseOrderJPAEntity;
import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.OrderItemJPAEntity;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderCommand;
import be.kdg.prog6.common.domain.OrderStatus;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderCreatedEvent;
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

    public ActivePurchaseOrder(ActivePurchaseOrderJPAEntity activePurchaseOrderJPAEntity) {
        this.purchaseOrderUUID = new PurchaseOrderUUID(activePurchaseOrderJPAEntity.getPurchaseOrderUUID());
        this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(activePurchaseOrderJPAEntity.getCustomerUUID());
        this.orderNumber = activePurchaseOrderJPAEntity.getOrderNumber();
        this.deliveryDate = activePurchaseOrderJPAEntity.getDeliveryDate();
        this.dateReceived = activePurchaseOrderJPAEntity.getDateReceived();
        this.address = activePurchaseOrderJPAEntity.getAddress();
        this.orderStatus = activePurchaseOrderJPAEntity.getOrderStatus();
        this.orderItems = activePurchaseOrderJPAEntity.getOrderItems().stream().map(OrderItem::new).toList();
    }

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

        public OrderItem(OrderItemJPAEntity orderItemJPAEntity) {
            this.orderItemUUID = orderItemJPAEntity.getOrderItemUUID();
            this.purchaseOrderUUID = orderItemJPAEntity.getPurchaseOrder().getPurchaseOrderUUID();
            this.materialUUID = orderItemJPAEntity.getMaterialUUID();
            this.quantity = orderItemJPAEntity.getQuantity();
            this.price = orderItemJPAEntity.getPrice();
        }
    }

    public ActivePurchaseOrder(PurchaseOrderCreatedEvent purchaseOrderCreatedEvent){
        this.purchaseOrderUUID = new PurchaseOrderUUID(purchaseOrderCreatedEvent.purchaseOrderUUID());
        this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(purchaseOrderCreatedEvent.customerUUID());
        this.orderNumber = purchaseOrderCreatedEvent.orderNumber();
        this.deliveryDate = LocalDateTime.parse(purchaseOrderCreatedEvent.deliveryDate());
        this.dateReceived = LocalDateTime.now();
        this.address = purchaseOrderCreatedEvent.address();
        this.orderStatus = purchaseOrderCreatedEvent.orderStatus();
        this.orderItems = purchaseOrderCreatedEvent.orderItems().stream().map(orderItem -> new OrderItem(
                orderItem.materialUUID(),
                orderItem.quantity(),
                orderItem.price()
        )).toList();
    }

    public void update(UpdatePurchaseOrderCommand updatePurchaseOrderCommand){
        this.address = updatePurchaseOrderCommand.address();
        this.deliveryDate = updatePurchaseOrderCommand.deliveryDate();
        this.orderStatus = updatePurchaseOrderCommand.orderStatus();
        this.orderItems = updatePurchaseOrderCommand.orderItems();
    }
}
