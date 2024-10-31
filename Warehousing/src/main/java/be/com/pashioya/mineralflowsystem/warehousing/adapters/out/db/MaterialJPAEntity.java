package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
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

    public MaterialJPAEntity(Material material){
        this.materialUUID = material.getMaterialUUID().uuid();
        this.name = material.getName();
        this.description = material.getDescription();
        this.price = material.getPrice();
        this.storagePrice = material.getStoragePrice();
    }
}
