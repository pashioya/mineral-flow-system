package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreatePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class PurchaseOrderDBAdapter implements CreatePurchaseOrderPort {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJPAEntity purchaseOrderJPAEntity = new PurchaseOrderJPAEntity(purchaseOrder);
        purchaseOrderRepository.save(purchaseOrderJPAEntity);
    }
}
