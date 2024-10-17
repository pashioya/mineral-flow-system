package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

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
    private double quantity;
    private LocalDateTime dateReceived;
}
