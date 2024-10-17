package be.com.pashioya.mineralflowsystem.warehousing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse {
    private WareHouseUUID warehouseUUID;
    private int warehouseNumber;
    private WarehouseCustomer customer;
    private double capacity;
    private Material material;

    public record WareHouseUUID(UUID uuid) {
    }
}
