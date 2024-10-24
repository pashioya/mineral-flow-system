package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreatePurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.LoadPurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.UpdatePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class PurchaseOrderDBAdapter implements CreatePurchaseOrderPort, UpdatePurchaseOrderPort, LoadPurchaseOrderPort {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJPAEntity purchaseOrderJPAEntity = new PurchaseOrderJPAEntity(purchaseOrder);
        purchaseOrderRepository.save(purchaseOrderJPAEntity);
    }

    @Override
    public Optional<PurchaseOrder> loadPurchaseOrder(UUID purchaseOrderUUID) {
        return purchaseOrderRepository.findById(purchaseOrderUUID).map(PurchaseOrder::new);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJPAEntity purchaseOrderJPAEntity = new PurchaseOrderJPAEntity(purchaseOrder);
        purchaseOrderRepository.save(purchaseOrderJPAEntity);
    }
}
