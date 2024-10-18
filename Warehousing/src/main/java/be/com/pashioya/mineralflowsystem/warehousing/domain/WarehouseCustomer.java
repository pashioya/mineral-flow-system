package be.com.pashioya.mineralflowsystem.warehousing.domain;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.CustomerProjectionJPAEntity;
import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WarehouseCustomer {
    private WarehouseCustomerUUID warehouseCustomerUUID;
    private String name;
    private String address;
    private String email;


    public record WarehouseCustomerUUID(UUID uuid) {
    }

    public WarehouseCustomer(CustomerProjectionJPAEntity customerProjectionJPAEntity) {
        this.warehouseCustomerUUID = new WarehouseCustomerUUID(customerProjectionJPAEntity.getCustomerUUID());
        this.name = customerProjectionJPAEntity.getName();
        this.address = customerProjectionJPAEntity.getAddress();
        this.email = customerProjectionJPAEntity.getEmail();
    }

    public WarehouseCustomer(CustomerCreatedEvent customerCreatedEvent) {
        this.warehouseCustomerUUID = new WarehouseCustomerUUID(customerCreatedEvent.customerUUID());
        this.name = customerCreatedEvent.name();
        this.address = customerCreatedEvent.address();
        this.email = customerCreatedEvent.email();
    }

}
