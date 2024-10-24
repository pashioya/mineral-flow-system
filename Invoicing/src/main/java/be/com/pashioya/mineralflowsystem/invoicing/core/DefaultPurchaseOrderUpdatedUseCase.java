package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.PurchaseOrderUpdatedUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.LoadPurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.UpdatePurchaseOrderPort;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderUpdatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultPurchaseOrderUpdatedUseCase implements PurchaseOrderUpdatedUseCase {

    UpdatePurchaseOrderPort updatePurchaseOrderPort;
    LoadPurchaseOrderPort loadPurchaseOrderPort;


    @Override
    public void updatePurchaseOrder(PurchaseOrderUpdatedEvent purchaseOrderEvent) {
        PurchaseOrder purchaseOrder = loadPurchaseOrderPort.loadPurchaseOrder(purchaseOrderEvent.purchaseOrderUUID()).orElseThrow();
        purchaseOrder.setAddress(purchaseOrderEvent.address());
        purchaseOrder.setOrderStatus(purchaseOrderEvent.orderStatus());
        purchaseOrder.setDeliveryDate(LocalDateTime.parse(purchaseOrderEvent.deliveryDate()));

            // Ensure the order items list is mutable
        List<PurchaseOrder.OrderItem> mutableOrderItems = new ArrayList<>(purchaseOrder.getOrderItems());

        // Update order items without breaking the collection reference
        List<PurchaseOrder.OrderItem> newOrderItems = purchaseOrderEvent.orderItems()
                .stream()
                .map(PurchaseOrder.OrderItem::new)
                .toList();

        // Remove existing items that are not in the updated list
        mutableOrderItems.removeIf(existingItem ->
                newOrderItems.stream().noneMatch(newItem -> newItem.equals(existingItem))
        );

        // Add or update new items
        for (PurchaseOrder.OrderItem newItem : newOrderItems) {
            if (!mutableOrderItems.contains(newItem)) {
                mutableOrderItems.add(newItem);
            }
        }

        // Set the updated mutable list back to the purchase order
        purchaseOrder.setOrderItems(mutableOrderItems);

        // Persist the updated purchase order
        updatePurchaseOrderPort.updatePurchaseOrder(purchaseOrder);
    }
}
