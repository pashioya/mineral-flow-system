package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;

import java.util.Optional;
import java.util.UUID;

public interface LoadPurchaseOrderPort {
    Optional<PurchaseOrder> loadPurchaseOrder(UUID purchaseOrderUUID);
}
