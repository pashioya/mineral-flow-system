package be.com.pashioya.mineralflowsystem.invoicing.adapters.in.amqp;


import be.com.pashioya.mineralflowsystem.invoicing.adapters.in.PurchaseOrderEventHandler;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.PurchaseOrderUpdatedUseCase;
import be.kdg.prog6.common.events.EventCatalog;


import be.kdg.prog6.common.facades.invoicing.PurchaseOrderEvent;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderUpdatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class PurchaseOrderUpdatedAMQPAdapter implements PurchaseOrderEventHandler<PurchaseOrderUpdatedEvent> {

    PurchaseOrderUpdatedUseCase purchaseOrderUpdatedUseCase;
        private final Logger logger = LoggerFactory.getLogger(PurchaseOrderUpdatedAMQPAdapter.class);


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.PURCHASE_ORDER_UPDATED == eventCatalog;
    }

    @Override
    public PurchaseOrderUpdatedEvent map(String eventBody) {
        try {
            return new ObjectMapper().readValue(eventBody, PurchaseOrderUpdatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to map event body to PurchaseOrderUpdatedEvent", e);
        }
    }

    @Override
    public void handle(PurchaseOrderEvent purchaseOrderEvent) {
        logger.info("Received PurchaseOrderUpdatedEvent: {}", purchaseOrderEvent);
        purchaseOrderUpdatedUseCase.updatePurchaseOrder((PurchaseOrderUpdatedEvent) purchaseOrderEvent);
    }
}
