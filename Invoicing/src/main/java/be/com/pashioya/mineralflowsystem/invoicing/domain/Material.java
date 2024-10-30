package be.com.pashioya.mineralflowsystem.invoicing.domain;


import be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db.MaterialJPAEntity;
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
}
