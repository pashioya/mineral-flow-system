package be.com.pashioya.mineralflowsystem.invoicing.adapters.in;

import be.com.pashioya.mineralflowsystem.invoicing.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.MaterialEvent;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RabbitEventHandler {

    private final List<PurchaseOrderEventHandler<? extends PurchaseOrderEvent>> purchaseOrderEventHandlers;
    private final List<MaterialEventHandler<? extends MaterialEvent>> materialEventHandlers;
    private final Logger logger = LoggerFactory.getLogger(RabbitEventHandler.class);

    @RabbitListener(queues = RabbitMQModuleTopology.PURCHASE_ORDER_UPDATED_ROUTING_KEY, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receivePurchaseOrderUpdatedEventMessage(EventMessage eventMessage) {
        logger.info("Received purchase order event message: {}", eventMessage);
        purchaseOrderEventHandlers.stream()
                .filter(
                        purchaseOrderEventHandlers ->
                                purchaseOrderEventHandlers.appliesTo(
                                        eventMessage.getEventHeader().getEventCatalog()
                                )
                )
                .forEach(
                        purchaseOrderEventHandlers ->
                                purchaseOrderEventHandlers.receive(eventMessage).handle(
                                        purchaseOrderEventHandlers.map(eventMessage.getEventBody())
                                )
                );
    }

    @RabbitListener(queues = RabbitMQModuleTopology.MATERIAL_CREATED_ROUTING_KEY, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveMaterialCreatedEventMessage(EventMessage eventMessage) {
        materialEventHandlers.stream()
                .filter(
                        materialEventHandlers ->
                                materialEventHandlers.appliesTo(
                                        eventMessage.getEventHeader().getEventCatalog()
                                )
                )
                .forEach(
                        materialEventHandlers ->
                                materialEventHandlers.receive(eventMessage).handle(
                                        materialEventHandlers.map(eventMessage.getEventBody())
                                )
                );
    }
}
