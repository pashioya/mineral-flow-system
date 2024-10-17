package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadMaterialsUseCase {
    List<Material> loadAllMaterials();
    Optional<Material> loadMaterial(UUID materialUUID);
}
