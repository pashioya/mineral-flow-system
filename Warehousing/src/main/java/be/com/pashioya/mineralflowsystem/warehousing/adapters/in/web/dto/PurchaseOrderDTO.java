package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.kdg.prog6.common.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PurchaseOrderDTO {
    private UUID purchaseOrderUUID;
    private UUID warehouseCustomerUUID;
    private String orderNumber;
    private LocalDateTime deliveryDate;
    private LocalDateTime dateReceived;
    private String address;
    private OrderStatus orderStatus;
    private List<OrderItemDTO> orderItems;

    public PurchaseOrderDTO(ActivePurchaseOrder activePurchaseOrder) {
        this.purchaseOrderUUID = activePurchaseOrder.getPurchaseOrderUUID().uuid();
        this.warehouseCustomerUUID = activePurchaseOrder.getWarehouseCustomerUUID().uuid();
        this.orderNumber = activePurchaseOrder.getOrderNumber();
        this.deliveryDate = activePurchaseOrder.getDeliveryDate();
        this.dateReceived = activePurchaseOrder.getDateReceived();
        this.address = activePurchaseOrder.getAddress();
        this.orderStatus = activePurchaseOrder.getOrderStatus();
        this.orderItems = activePurchaseOrder.getOrderItems().stream().map(OrderItemDTO::new).toList();
    }
}
