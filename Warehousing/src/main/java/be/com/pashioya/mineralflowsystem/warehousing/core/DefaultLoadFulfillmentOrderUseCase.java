package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadFulfillmentOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadFulfillmentOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class DefaultLoadFulfillmentOrderUseCase implements LoadFulfillmentOrderUseCase {

    private final LoadFulfillmentOrderPort loadFulfillmentOrderPort;

    @Override
    public List<FulfillmentOrder> loadAllFulfillmentOrders() {
        return loadFulfillmentOrderPort.loadAllFulfillmentOrders();
    }
}
