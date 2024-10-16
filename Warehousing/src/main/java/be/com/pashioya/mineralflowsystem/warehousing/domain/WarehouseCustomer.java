package be.com.pashioya.mineralflowsystem.warehousing.domain;

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
}
