package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
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
    public void createOrderItems(List<PurchaseOrder.OrderItem> orderItems, UUID purchaseOrderUUID) {
        PurchaseOrderJPAEntity purchaseOrder = purchaseOrderRepository.findById(purchaseOrderUUID).orElseThrow(()
                -> new RuntimeException("Purchase Order not found"));

        orderItems.stream().map(orderItem -> new OrderItemJPAEntity(orderItem, purchaseOrder)).forEach(orderItemRepository::save);
    }
}
