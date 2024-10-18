package be.com.pashioya.mineralflowsystem.warehousing.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderCreatedEvent;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderEvent;

public interface PurchaseOrderEventHandler<T extends PurchaseOrderEvent> {
    boolean appliesTo(EventCatalog eventCatalog);

    default PurchaseOrderEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }

    PurchaseOrderCreatedEvent map(String eventBody);

    void handle(PurchaseOrderEvent attractionEventBody);
}
