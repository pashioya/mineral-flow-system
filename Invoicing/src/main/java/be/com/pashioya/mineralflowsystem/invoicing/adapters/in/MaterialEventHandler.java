package be.com.pashioya.mineralflowsystem.invoicing.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.MaterialCreatedEvent;
import be.kdg.prog6.common.facades.invoicing.MaterialEvent;

public interface MaterialEventHandler<T extends MaterialEvent> {
    boolean appliesTo(EventCatalog eventCatalog);

    default MaterialEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }

    MaterialCreatedEvent map(String eventBody);

    void handle(MaterialEvent materialEvent);
}
