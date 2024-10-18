package be.com.pashioya.mineralflowsystem.warehousing.ports.out;


import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;

public interface CreateActivePurchaseOrderPort {
    void createActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder);
}
