package be.com.pashioya.mineralflowsystem.invoicing.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderEvent;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderUpdatedEvent;

public interface PurchaseOrderEventHandler<T extends PurchaseOrderEvent> {
    boolean appliesTo(EventCatalog eventCatalog);

    default PurchaseOrderEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }

    PurchaseOrderUpdatedEvent map(String eventBody);

    void handle(PurchaseOrderEvent purchaseOrderEvent);
}
