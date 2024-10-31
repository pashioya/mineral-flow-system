package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class MaterialDTO {
    private UUID materialUUID;
    private String name;
    private String description;
    private double price;
    private double storagePrice;

    public MaterialDTO(Material material) {
        this.materialUUID = material.getMaterialUUID().uuid();
        this.name = material.getName();
        this.description = material.getDescription();
        this.price = material.getPrice();
        this.storagePrice = material.getStoragePrice();
    }

}
