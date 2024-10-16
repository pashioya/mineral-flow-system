package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.OrderItem;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateOrderItemPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class OrderItemDBAdapter implements CreateOrderItemPort {

    private final OrderItemRepository orderItemRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public void createOrderItems(List<OrderItem> orderItems, UUID purchaseOrderUUID) {
        PurchaseOrderJPAEntity purchaseOrder = purchaseOrderRepository.findById(purchaseOrderUUID).orElseThrow(()
                -> new RuntimeException("Purchase Order not found"));

        orderItems.stream().map(orderItem -> {
            OrderItemJPAEntity orderItemJPAEntity = new OrderItemJPAEntity();
            orderItemJPAEntity.setOrderItemUUID(orderItem.getOrderItemUUID());
            orderItemJPAEntity.setPurchaseOrder(purchaseOrder);
            orderItemJPAEntity.setMaterialUUID(orderItem.getMaterialUUID());
            orderItemJPAEntity.setQuantity(orderItem.getQuantity());
            orderItemJPAEntity.setPrice(orderItem.getPrice());
            return orderItemJPAEntity;
        }).forEach(orderItemRepository::save);
    }
}