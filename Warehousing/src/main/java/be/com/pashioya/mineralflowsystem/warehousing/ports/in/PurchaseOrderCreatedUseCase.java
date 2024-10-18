package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.kdg.prog6.common.facades.invoicing.PurchaseOrderCreatedEvent;

public interface PurchaseOrderCreatedUseCase {
    void createActivePurchaseOrder(PurchaseOrderCreatedEvent purchaseOrderEvent);
}
