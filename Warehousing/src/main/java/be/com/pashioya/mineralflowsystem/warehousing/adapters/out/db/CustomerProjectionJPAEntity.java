package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;


import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Entity
@Table(name = "warehouse_customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerProjectionJPAEntity {
    @Id
    private UUID customerUUID;
    private String name;
    private String address;
    private String email;

    public CustomerProjectionJPAEntity(WarehouseCustomer warehouseCustomer){
        this.customerUUID = warehouseCustomer.getWarehouseCustomerUUID().uuid();
        this.name = warehouseCustomer.getName();
        this.address = warehouseCustomer.getAddress();
        this.email = warehouseCustomer.getEmail();
    }
}
