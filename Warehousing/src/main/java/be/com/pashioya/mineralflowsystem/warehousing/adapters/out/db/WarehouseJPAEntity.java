package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WarehouseJPAEntity {
    @Id
    private UUID warehouseUUID;
    private UUID customerUUID;
    private int warehouseNumber;
    private UUID materialUUID;
    private double capacity;
}
