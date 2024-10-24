package be.com.pashioya.mineralflowsystem.invoicing.ports.in;

import be.kdg.prog6.common.facades.invoicing.PurchaseOrderUpdatedEvent;

public interface PurchaseOrderUpdatedUseCase {
    void updatePurchaseOrder(PurchaseOrderUpdatedEvent purchaseOrderEvent);
}
