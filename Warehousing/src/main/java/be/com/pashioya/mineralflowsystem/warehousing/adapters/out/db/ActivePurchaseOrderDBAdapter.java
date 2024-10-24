package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateActivePurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadActivePurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateActivePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ActivePurchaseOrderDBAdapter implements CreateActivePurchaseOrderPort, UpdateActivePurchaseOrderPort, LoadActivePurchaseOrderPort {

    ActivePurchaseOrderRepository activePurchaseOrderRepository;

    @Override
    public void createActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder) {
        ActivePurchaseOrderJPAEntity activePurchaseOrderJPAEntity =  new ActivePurchaseOrderJPAEntity(activePurchaseOrder);
        activePurchaseOrderRepository.save(activePurchaseOrderJPAEntity);
    }

    @Override
    public void updateActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder) {
        ActivePurchaseOrderJPAEntity activePurchaseOrderJPAEntity = activePurchaseOrderRepository.findById(activePurchaseOrder.getPurchaseOrderUUID().uuid())
                .orElseThrow();
        ActivePurchaseOrderJPAEntity updatedEntity = new ActivePurchaseOrderJPAEntity(activePurchaseOrder);
        // just to make sure the UUID is not changed
        updatedEntity.setPurchaseOrderUUID(activePurchaseOrderJPAEntity.getPurchaseOrderUUID());
        activePurchaseOrderRepository.save(updatedEntity);
    }

    @Override
    public Optional<ActivePurchaseOrder> loadActivePurchaseOrder(UUID purchaseOrderUUID) {
        return activePurchaseOrderRepository.findById(purchaseOrderUUID).map(ActivePurchaseOrder::new);
    }

    @Override
    public List<ActivePurchaseOrder> loadAllActivePurchaseOrders() {
        return activePurchaseOrderRepository.findAll().stream().map(ActivePurchaseOrder::new).toList();
    }
}
