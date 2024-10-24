package be.com.pashioya.mineralflowsystem.invoicing.domain;

import be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db.OrderItemJPAEntity;
import be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db.PurchaseOrderJPAEntity;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderCommand;
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
public class PurchaseOrder {
    private PurchaseOrderUUID purchaseOrderUUID;
    private Customer.CustomerUUID customerUUID;
    private String orderNumber;
    private LocalDateTime deliveryDate;
    private LocalDateTime dateReceived;
    private String address;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;

    public PurchaseOrder(PurchaseOrderJPAEntity purchaseOrderJPA) {
        this.purchaseOrderUUID = new PurchaseOrderUUID(purchaseOrderJPA.getPurchaseOrderUUID());
        this.customerUUID = new Customer.CustomerUUID(purchaseOrderJPA.getCustomerUUID());
        this.orderNumber = purchaseOrderJPA.getOrderNumber();
        this.deliveryDate = purchaseOrderJPA.getDeliveryDate();
        this.dateReceived = purchaseOrderJPA.getDateReceived();
        this.address = purchaseOrderJPA.getAddress();
        this.orderStatus = purchaseOrderJPA.getOrderStatus();
        this.orderItems = purchaseOrderJPA.getOrderItems().stream().map(OrderItem::new).toList();
    }

    public record PurchaseOrderUUID(UUID uuid) {
    }

    public PurchaseOrder(CreatePurchaseOrderCommand createPurchaseOrderCommand) {
        this.purchaseOrderUUID = new PurchaseOrderUUID(UUID.randomUUID());
        this.customerUUID = new Customer.CustomerUUID(createPurchaseOrderCommand.customerUUID());
        this.orderNumber = createPurchaseOrderCommand.orderNumber();
        this.deliveryDate = createPurchaseOrderCommand.deliveryDate();
        this.dateReceived = LocalDateTime.now();
        this.address = createPurchaseOrderCommand.address();
        this.orderStatus = OrderStatus.CREATED;
        this.orderItems = createPurchaseOrderCommand.orderItems();
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

        public OrderItem(be.kdg.prog6.common.facades.invoicing.OrderItem orderItem) {
            this.orderItemUUID = orderItem.orderItemUUID();
            this.purchaseOrderUUID = orderItem.purchaseOrderUUID();
            this.materialUUID = orderItem.materialUUID();
            this.quantity = orderItem.quantity();
            this.price = orderItem.price();
        }
    }
}
