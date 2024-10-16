package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.UUID;

@Entity
@Table(name = "materials")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NaturalId
    @Setter
    private UUID materialUUID;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private double price;
    @Setter
    private double storagePrice;
}
