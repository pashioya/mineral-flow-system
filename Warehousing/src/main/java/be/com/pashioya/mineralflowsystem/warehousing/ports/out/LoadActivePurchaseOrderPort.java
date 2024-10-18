package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadActivePurchaseOrderPort {
    Optional<ActivePurchaseOrder> loadActivePurchaseOrder(UUID purchaseOrderUUID);
    List<ActivePurchaseOrder> loadAllActivePurchaseOrders();
}
