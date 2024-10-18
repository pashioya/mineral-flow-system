package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.amqp;


import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.PurchaseOrderEventHandler;

import be.com.pashioya.mineralflowsystem.warehousing.ports.in.PurchaseOrderCreatedUseCase;
import be.kdg.prog6.common.events.EventCatalog;


import be.kdg.prog6.common.facades.invoicing.PurchaseOrderCreatedEvent;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class PurchaseOrderCreatedAMQPAdapter implements PurchaseOrderEventHandler<PurchaseOrderCreatedEvent> {

    PurchaseOrderCreatedUseCase purchaseOrderCreatedUseCase;

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.PURCHASE_ORDER_CREATED == eventCatalog;
    }

    @Override
    public PurchaseOrderCreatedEvent map(String eventBody) {
        try {
            return new ObjectMapper().readValue(eventBody, PurchaseOrderCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to map event body to PurchaseOrderCreatedEvent", e);
        }
    }

    @Override
    public void handle(PurchaseOrderEvent attractionEventBody) {
        purchaseOrderCreatedUseCase.createActivePurchaseOrder((PurchaseOrderCreatedEvent) attractionEventBody);
    }
}
