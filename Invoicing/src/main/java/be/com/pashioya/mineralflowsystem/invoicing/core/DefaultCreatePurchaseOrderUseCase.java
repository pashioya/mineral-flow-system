package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateOrderItemPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreatePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCreatePurchaseOrderUseCase implements CreatePurchaseOrderUseCase {

    private final List<CreatePurchaseOrderPort> createPurchaseOrderPort;
    private final CreateOrderItemPort createOrderItemPort;

    @Override
    public void createPurchaseOrder(CreatePurchaseOrderCommand command) {
        PurchaseOrder.PurchaseOrderUUID purchaseOrderUUID = new PurchaseOrder.PurchaseOrderUUID(UUID.randomUUID());



        PurchaseOrder purchaseOrder = new PurchaseOrder(
                purchaseOrderUUID,
                new Customer.CustomerUUID(command.customerUUID()),
                command.orderNumber(),
                command.orderDate(),
                command.orderItems()
        );
        createPurchaseOrderPort.forEach(port -> port.createPurchaseOrder(purchaseOrder));

        command.orderItems().forEach(orderItem -> orderItem.setPurchaseOrderUUID(purchaseOrderUUID.uuid()));
        createOrderItemPort.createOrderItems(command.orderItems(), purchaseOrderUUID.uuid());

    }
}
