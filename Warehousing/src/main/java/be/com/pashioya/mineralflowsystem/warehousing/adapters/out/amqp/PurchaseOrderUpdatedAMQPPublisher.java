package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.amqp;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.config.RabbitMQModuleTopology;
import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateActivePurchaseOrderPort;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.OrderItem;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderUpdatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PurchaseOrderUpdatedAMQPPublisher implements UpdateActivePurchaseOrderPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void updateActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder) {
        var eventHeader = EventHeader.builder()
                .eventID(UUID.randomUUID())
                .eventCatalog(EventCatalog.PURCHASE_ORDER_UPDATED).build();
        var eventBody = new PurchaseOrderUpdatedEvent(
                activePurchaseOrder.getPurchaseOrderUUID().uuid(),
                activePurchaseOrder.getAddress(),
                activePurchaseOrder.getDeliveryDate().toString(),
                activePurchaseOrder.getOrderStatus(),
                activePurchaseOrder.getOrderItems().stream().map(orderItem -> new OrderItem(
                        orderItem.getOrderItemUUID(),
                        orderItem.getPurchaseOrderUUID(),
                        orderItem.getMaterialUUID(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                )).toList()
        );

        try {
            rabbitTemplate.convertAndSend("purchase-order", RabbitMQModuleTopology.PURCHASE_ORDER_UPDATED_ROUTING_KEY,
                                        EventMessage.builder()
                                                .eventHeader(eventHeader)
                                                .eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
