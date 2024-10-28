package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
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
public class FulfillmentOrderDTO {
    private UUID fulfillmentOrderUUID;
    private UUID purchaseOrderUUID;
    private UUID customerUUID;
    private String orderNumber;
    private LocalDateTime dateOrdered;
    private LocalDateTime expectedDeliveryDate;
    private OrderStatus orderStatus;
    private List<FulfillmentOrderItemDTO> orderItems;



    public FulfillmentOrderDTO(FulfillmentOrder fulfillmentOrder) {
        this.fulfillmentOrderUUID = fulfillmentOrder.getFulfillmentOrderUUID().uuid();
        this.purchaseOrderUUID = fulfillmentOrder.getPurchaseOrderUUID();
        this.customerUUID = fulfillmentOrder.getWarehouseCustomerUUID().uuid();
        this.orderNumber = fulfillmentOrder.getOrderNumber();
        this.dateOrdered = fulfillmentOrder.getDateOrdered();
        this.expectedDeliveryDate = fulfillmentOrder.getExpectedDeliveryDate();
        this.orderStatus = fulfillmentOrder.getOrderStatus();
        this.orderItems = fulfillmentOrder.getOrderItems().stream().map(FulfillmentOrderItemDTO::new).toList();
    }
}
