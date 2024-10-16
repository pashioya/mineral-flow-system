package be.com.pashioya.mineralflowsystem.warehousing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Material {
    private MaterialUUID uuid;
    private String name;
    private String description;
    private double price;
    private double storagePrice;

    public record MaterialUUID(UUID uuid) {
    }
}
