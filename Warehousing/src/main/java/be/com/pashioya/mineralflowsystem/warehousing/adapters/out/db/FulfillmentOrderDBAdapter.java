package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.FulfillmentOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateFulfillmentOrderPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadFulfillmentOrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@AllArgsConstructor
@Repository
public class FulfillmentOrderDBAdapter implements CreateFulfillmentOrderPort, LoadFulfillmentOrderPort {

    private final FulfillmentOrderRepository fulfillmentOrderRepository;

    @Override
    public void createFulfillmentOrder(FulfillmentOrder fulfillmentOrder) {
        fulfillmentOrderRepository.save(new FulfillmentOrderJPAEntity(fulfillmentOrder));
    }

    @Override
    public List<FulfillmentOrder> loadAllFulfillmentOrders() {
        return fulfillmentOrderRepository.findAll().stream().map(FulfillmentOrder::new).toList();
    }
}
