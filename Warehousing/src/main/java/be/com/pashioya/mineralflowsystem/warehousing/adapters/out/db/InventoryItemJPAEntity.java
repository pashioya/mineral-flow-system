package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.InventoryItem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Entity
@Table(name = "inventory_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryItemJPAEntity {
    @Id
    private UUID inventoryItemUUID;
    private UUID customerUUID;
    private UUID materialUUID;
    private UUID warehouseUUID;
    private int quantity;
    private LocalDateTime dateReceived;

    public InventoryItemJPAEntity(InventoryItem inventoryItem){
        this.inventoryItemUUID = inventoryItem.getUuid().uuid();
        this.customerUUID = inventoryItem.getWarehouseCustomerUUID().uuid();
        this.materialUUID = inventoryItem.getMaterialUUID().uuid();
        this.warehouseUUID = inventoryItem.getWareHouseUUID().uuid();
        this.quantity = inventoryItem.getQuantity();
        this.dateReceived = inventoryItem.getDateReceived();
    }
}
