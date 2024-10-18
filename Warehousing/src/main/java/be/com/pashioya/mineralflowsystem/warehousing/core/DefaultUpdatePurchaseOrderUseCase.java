package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadActivePurchaseOrderPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateActivePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUpdatePurchaseOrderUseCase implements UpdatePurchaseOrderUseCase {
    private final LoadActivePurchaseOrderPort loadActivePurchaseOrderPort;
    private final UpdateActivePurchaseOrderPort updateActivePurchaseOrderPort;

    @Override
    public void updatePurchaseOrder(UpdatePurchaseOrderCommand command) {
        ActivePurchaseOrder activePurchaseOrder = loadActivePurchaseOrderPort.loadActivePurchaseOrder(command.purchaseOrderUUID()).orElseThrow();
        activePurchaseOrder.update(command);
        updateActivePurchaseOrderPort.updateActivePurchaseOrder(new ActivePurchaseOrder());
    }
}
