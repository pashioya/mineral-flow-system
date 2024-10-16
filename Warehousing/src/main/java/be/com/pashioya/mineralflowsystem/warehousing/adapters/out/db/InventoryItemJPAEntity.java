package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryItemJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NaturalId
    @Setter
    private UUID inventoryItemUUID;
    @Setter
    private UUID customerUUID;
    @Setter
    private UUID materialUUID;
    @Setter
    private double quantity;
    @Setter
    private LocalDateTime dateReceived;
}
