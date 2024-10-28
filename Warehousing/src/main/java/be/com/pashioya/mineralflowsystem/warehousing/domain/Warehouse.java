package be.com.pashioya.mineralflowsystem.warehousing.domain;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.WarehouseJPAEntity;
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
    private WarehouseCustomer.WarehouseCustomerUUID warehouseCustomerUUID;
    private double capacity;
    private Material.MaterialUUID materialUUID;

    public Warehouse(WarehouseJPAEntity warehouseJPAEntity) {
        this.warehouseUUID = new WareHouseUUID(warehouseJPAEntity.getWarehouseUUID());
        this.warehouseNumber = warehouseJPAEntity.getWarehouseNumber();
        this.capacity = warehouseJPAEntity.getCapacity();
        if (warehouseJPAEntity.getCustomerUUID() != null) {
            this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(warehouseJPAEntity.getCustomerUUID());
        }
        if (warehouseJPAEntity.getMaterialUUID() != null) {
            this.materialUUID = new Material.MaterialUUID(warehouseJPAEntity.getMaterialUUID());
        }
    }

    public record WareHouseUUID(UUID uuid) {
    }
}
