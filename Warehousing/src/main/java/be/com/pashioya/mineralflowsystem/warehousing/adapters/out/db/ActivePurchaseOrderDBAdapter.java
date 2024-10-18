package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateActivePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivePurchaseOrderDBAdapter implements CreateActivePurchaseOrderPort {

    ActivePurchaseOrderRepository activePurchaseOrderRepository;

    @Override
    public void createActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder) {
        ActivePurchaseOrderJPAEntity activePurchaseOrderJPAEntity = new ActivePurchaseOrderJPAEntity();
        activePurchaseOrderJPAEntity.setPurchaseOrderUUID(activePurchaseOrder.getPurchaseOrderUUID().uuid());
        activePurchaseOrderJPAEntity.setDateReceived(activePurchaseOrder.getDateReceived());
        activePurchaseOrderJPAEntity.setDeliveryDate(activePurchaseOrder.getDeliveryDate());
        activePurchaseOrderJPAEntity.setAddress(activePurchaseOrder.getAddress());
        activePurchaseOrderJPAEntity.setCustomerUUID(activePurchaseOrder.getWarehouseCustomerUUID().uuid());
        activePurchaseOrderJPAEntity.setOrderStatus(activePurchaseOrder.getOrderStatus());
        activePurchaseOrderJPAEntity.setOrderNumber(activePurchaseOrder.getOrderNumber());
        activePurchaseOrderJPAEntity.setOrderItems(activePurchaseOrder.getOrderItems().stream().map(orderItem -> {
            OrderItemJPAEntity orderItemJPAEntity = new OrderItemJPAEntity();
            orderItemJPAEntity.setOrderItemUUID(orderItem.getOrderItemUUID());
            orderItemJPAEntity.setMaterialUUID(orderItem.getMaterialUUID());
            orderItemJPAEntity.setPurchaseOrder(activePurchaseOrderJPAEntity);
            orderItemJPAEntity.setQuantity(1);
            orderItemJPAEntity.setPrice(orderItem.getPrice());
            return orderItemJPAEntity;
        }).toList());
        activePurchaseOrderRepository.save(activePurchaseOrderJPAEntity);
    }
}
