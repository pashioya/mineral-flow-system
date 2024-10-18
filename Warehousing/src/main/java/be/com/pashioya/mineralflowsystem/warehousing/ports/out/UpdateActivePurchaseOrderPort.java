package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;

public interface UpdateActivePurchaseOrderPort {
    void updateActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder);
}
