package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateOrderItemPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreatePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultCreatePurchaseOrderUseCase implements CreatePurchaseOrderUseCase {

    private final List<CreatePurchaseOrderPort> createPurchaseOrderPort;
    private final CreateOrderItemPort createOrderItemPort;

    @Override
    public void createPurchaseOrder(CreatePurchaseOrderCommand command) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(command);
        createPurchaseOrderPort.forEach(port -> port.createPurchaseOrder(purchaseOrder));

        command.orderItems().forEach(orderItem -> orderItem.setPurchaseOrderUUID(purchaseOrder.getPurchaseOrderUUID().uuid()));
        createOrderItemPort.createOrderItems(command.orderItems(), purchaseOrder.getPurchaseOrderUUID().uuid());
    }
}
