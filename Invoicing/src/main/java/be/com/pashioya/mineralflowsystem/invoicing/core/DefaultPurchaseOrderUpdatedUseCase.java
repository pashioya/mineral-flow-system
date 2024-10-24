package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.PurchaseOrderUpdatedUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.LoadPurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.UpdatePurchaseOrderPort;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderUpdatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        purchaseOrder.setDeliveryDate(purchaseOrderEvent.deliveryDate());
        purchaseOrder.setOrderItems(purchaseOrderEvent.orderItems().stream().map(PurchaseOrder.OrderItem::new).toList());
        updatePurchaseOrderPort.updatePurchaseOrder(purchaseOrder);
    }
}
