package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto;


import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class FulfillmentOrderItemDTO {
    private UUID fulfillmentOrderItemUUID;
    private UUID materialUUID;
    private int quantity;
    private double price;

    public FulfillmentOrderItemDTO(FulfillmentOrder.FulfillmentOrderItem fulfillmentOrderItem) {
        this.fulfillmentOrderItemUUID = fulfillmentOrderItem.getFulfillmentOrderItemUUID();
        this.materialUUID = fulfillmentOrderItem.getMaterialUUID();
        this.quantity = fulfillmentOrderItem.getQuantity();
        this.price = fulfillmentOrderItem.getPrice();
    }
}
