package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface LoadPurchaseOrderUseCase {
    List<ActivePurchaseOrder> loadAllPurchaseOrders();
    Optional<ActivePurchaseOrder> loadPurchaseOrder(UUID purchaseOrderUUID);
}
