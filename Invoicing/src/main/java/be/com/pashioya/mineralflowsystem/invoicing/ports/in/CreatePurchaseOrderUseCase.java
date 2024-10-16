package be.com.pashioya.mineralflowsystem.invoicing.ports.in;

public interface CreatePurchaseOrderUseCase {
    void createPurchaseOrder(CreatePurchaseOrderCommand command);
}
