package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.PurchaseOrderCreatedUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateActivePurchaseOrderPort;
import be.kdg.prog6.common.facades.invoicing.PurchaseOrderCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DefaultPurchaseOrderCreatedUseCase implements PurchaseOrderCreatedUseCase {
    private final CreateActivePurchaseOrderPort createActivePurchaseOrderPort;

    @Override
    public void createActivePurchaseOrder(PurchaseOrderCreatedEvent purchaseOrderEvent) {
        createActivePurchaseOrderPort.createActivePurchaseOrder(new ActivePurchaseOrder(
                new ActivePurchaseOrder.PurchaseOrderUUID(purchaseOrderEvent.purchaseOrderUUID()),
                new WarehouseCustomer.WarehouseCustomerUUID(purchaseOrderEvent.customerUUID()),
                purchaseOrderEvent.orderNumber(),
                LocalDateTime.parse(purchaseOrderEvent.deliveryDate()),
                LocalDateTime.now(),
                purchaseOrderEvent.address(),
                purchaseOrderEvent.orderStatus(),
                purchaseOrderEvent.orderItems().stream().map(orderItem -> new ActivePurchaseOrder.OrderItem(
                        orderItem.materialUUID(),
                        orderItem.quantity(),
                        orderItem.price()
                )).toList()
        ));
    }

}
