package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Material;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadMaterialPort {
    Optional<Material> loadMaterial(UUID materialUUID);
    List<Material> loadAllMaterials();
}
