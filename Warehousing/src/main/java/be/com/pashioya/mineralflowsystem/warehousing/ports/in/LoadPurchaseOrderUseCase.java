package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;

import java.util.List;


public interface LoadPurchaseOrderUseCase {
    List<ActivePurchaseOrder> loadAllPurchaseOrders();
}
