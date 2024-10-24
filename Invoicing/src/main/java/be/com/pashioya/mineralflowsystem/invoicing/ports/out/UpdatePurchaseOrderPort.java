package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;

public interface UpdatePurchaseOrderPort {
    void updatePurchaseOrder(PurchaseOrder purchaseOrder);
}
