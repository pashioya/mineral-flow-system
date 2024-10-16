package be.com.pashioya.mineralflowsystem.warehousing.adapters.in;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.CustomerEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.util.List;
@AllArgsConstructor
@Component
public class RabbitEventHandler {

    private final List<CustomerEventHandler<? extends CustomerEvent>> customerEventHandlers;
    private final Logger logger = LoggerFactory.getLogger(RabbitEventHandler.class);

    @RabbitListener(queues = RabbitMQModuleTopology.CUSTOMER_CREATED_ROUTING_KEY, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage(EventMessage eventMessage) {
        logger.info("Received event message: {}", eventMessage);
        customerEventHandlers.stream()
                .filter(
                        customerEventHandlers ->
                                customerEventHandlers.appliesTo(
                                        eventMessage.getEventHeader().getEventCatalog()
                                )
                )
                .forEach(
                        customerEventHandlers ->
                                customerEventHandlers.receive(eventMessage).handle(
                                        customerEventHandlers.map(eventMessage.getEventBody())
                                )
                );
    }
}
