package be.com.pashioya.mineralflowsystem.warehousing.domain;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db.MaterialJPAEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Material {
    private MaterialUUID materialUUID;
    private String name;
    private String description;
    private double price;
    private double storagePrice;

    public record MaterialUUID(UUID uuid) {
    }

    public Material(MaterialJPAEntity materialJPAEntity) {
        this.materialUUID = new MaterialUUID(materialJPAEntity.getMaterialUUID());
        this.name = materialJPAEntity.getName();
        this.description = materialJPAEntity.getDescription();
        this.price = materialJPAEntity.getPrice();
            this.storagePrice = materialJPAEntity.getStoragePrice();
    }

    public Material(String name, String description, double price, double storagePrice) {
        this.materialUUID = new MaterialUUID(UUID.randomUUID());
        this.name = name;
        this.description = description;
        this.price = price;
        this.storagePrice = storagePrice;
    }
}
