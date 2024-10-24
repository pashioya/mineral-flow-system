package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto;

import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class WarehouseCustomerDTO {
    private UUID warehouseCustomerUUID;
    private String name;
    private String address;
    private String email;

    public WarehouseCustomerDTO(WarehouseCustomer warehouseCustomer) {
        this.warehouseCustomerUUID = warehouseCustomer.getWarehouseCustomerUUID().uuid();
        this.name = warehouseCustomer.getName();
        this.address = warehouseCustomer.getAddress();
        this.email = warehouseCustomer.getEmail();
    }
}
