package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Entity
@Table(name = "materials")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialJPAEntity {
    @Id
    private UUID materialUUID;
    private String name;
    private String description;
    private double price;
    private double storagePrice;
}
