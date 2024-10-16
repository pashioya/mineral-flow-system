package be.com.pashioya.mineralflowsystem.warehousing.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;
import be.kdg.prog6.common.facades.invoicing.CustomerEvent;

public interface CustomerEventHandler<T extends CustomerEvent> {
    boolean appliesTo(EventCatalog eventCatalog);

    default CustomerEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }

    CustomerCreatedEvent map(String eventBody);

    void handle(CustomerEvent attractionEventBody);
}
