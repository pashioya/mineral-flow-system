package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreatePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@AllArgsConstructor
@Repository
public class PurchaseOrderDBAdapter implements CreatePurchaseOrderPort {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJPAEntity purchaseOrderJPAEntity = new PurchaseOrderJPAEntity();
        PurchaseOrder.PurchaseOrderUUID purchaseOrderUUID = purchaseOrder.getPurchaseOrderUUID();

        purchaseOrderJPAEntity.setPurchaseOrderUUID(purchaseOrderUUID.uuid());
        purchaseOrderJPAEntity.setCustomerUUID(purchaseOrder.getCustomerUUID().uuid());
        purchaseOrderJPAEntity.setOrderNumber(purchaseOrder.getOrderNumber());
        purchaseOrderJPAEntity.setAddress(purchaseOrder.getAddress());
        purchaseOrderJPAEntity.setOrderStatus(purchaseOrder.getOrderStatus());
        purchaseOrderJPAEntity.setDateReceived(LocalDateTime.now());
        purchaseOrderJPAEntity.setDeliveryDate(purchaseOrder.getDeliveryDate());
        purchaseOrderRepository.save(purchaseOrderJPAEntity);
    }
}
