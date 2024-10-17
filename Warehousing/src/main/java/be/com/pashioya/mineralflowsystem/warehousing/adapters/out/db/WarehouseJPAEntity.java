package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private UUID warehouseUUID;
    private UUID customerUUID;
    private int warehouseNumber;
    private UUID materialUUID;
    private double capacity;

}
