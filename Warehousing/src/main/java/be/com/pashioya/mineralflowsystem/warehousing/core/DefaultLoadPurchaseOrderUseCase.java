package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadPurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadActivePurchaseOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultLoadPurchaseOrderUseCase implements LoadPurchaseOrderUseCase {

    private final LoadActivePurchaseOrderPort loadActivePurchaseOrderPort;

    @Override
    public List<ActivePurchaseOrder> loadAllPurchaseOrders() {
        return loadActivePurchaseOrderPort.loadAllActivePurchaseOrders();
    }
}
