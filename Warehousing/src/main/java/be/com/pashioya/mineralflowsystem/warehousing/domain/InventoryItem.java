package be.com.pashioya.mineralflowsystem.warehousing.domain;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.InventoryItemJPAEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class InventoryItem {
    private InventoryItemUUID uuid;
    private WarehouseCustomer.WarehouseCustomerUUID warehouseCustomerUUID;
    private Material.MaterialUUID materialUUID;
    private Warehouse.WareHouseUUID wareHouseUUID;
    private int quantity;
    private LocalDateTime dateReceived;

    public record InventoryItemUUID(UUID uuid) {
    }

    public InventoryItem(InventoryItemJPAEntity inventoryItemJPAEntity) {
        this.uuid = new InventoryItemUUID(inventoryItemJPAEntity.getInventoryItemUUID());
        this.warehouseCustomerUUID = new WarehouseCustomer.WarehouseCustomerUUID(inventoryItemJPAEntity.getCustomerUUID());
        this.materialUUID = new Material.MaterialUUID(inventoryItemJPAEntity.getMaterialUUID());
        this.wareHouseUUID = new Warehouse.WareHouseUUID(inventoryItemJPAEntity.getWarehouseUUID());
        this.quantity = inventoryItemJPAEntity.getQuantity();
        this.dateReceived = inventoryItemJPAEntity.getDateReceived();
    }
}
