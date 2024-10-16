package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.amqp;

import be.com.pashioya.mineralflowsystem.invoicing.adapters.config.RabbitMQModuleTopology;
import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreatePurchaseOrderPort;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PurchaseOrderCreatedAMQPPublisher implements CreatePurchaseOrderPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(PurchaseOrderCreatedAMQPPublisher.class);


    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.PURCHASE_ORDER_CREATED).build();
        var eventBody = new PurchaseOrderCreatedEvent(
                purchaseOrder.getPurchaseOrderUUID().uuid(),
                purchaseOrder.getCustomerUUID().uuid(),
                purchaseOrder.getOrderNumber(),
                purchaseOrder.getOrderDate(),
                purchaseOrder.getOrderItems().stream().map(orderItem -> new PurchaseOrderCreatedEvent.OrderItem(
                        orderItem.getOrderItemUUID(),
                        orderItem.getPurchaseOrderUUID(),
                        orderItem.getMaterialUUID(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                )).toList()
        );

        try {
            rabbitTemplate.convertAndSend(
                    "invoicing", RabbitMQModuleTopology.PURCHASE_ORDER_CREATED_ROUTING_KEY,
                    EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (Exception e) {
            logger.error("Error while sending purchase order created event to RabbitMQ", e);
        }
    }
}
