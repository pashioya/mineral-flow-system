package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CustomerCreatedUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWareHouseCustomerPort;
import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultCustomerCreatedUseCase implements CustomerCreatedUseCase {

    private final CreateWareHouseCustomerPort createWareHouseCustomerPort;

    @Override
    public void createWarehouseCustomer(CustomerCreatedEvent customerEvent) {
        createWareHouseCustomerPort.createWareHouseCustomer(new WarehouseCustomer(customerEvent));
    }
}
