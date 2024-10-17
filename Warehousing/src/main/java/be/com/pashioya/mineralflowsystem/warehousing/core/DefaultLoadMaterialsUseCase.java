package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadMaterialsUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadMaterialPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultLoadMaterialsUseCase implements LoadMaterialsUseCase {
    private final LoadMaterialPort loadMaterialsPort;

    @Override
    public List<Material> loadAllMaterials() {
        return loadMaterialsPort.loadAllMaterials();
    }

    @Override
    public Optional<Material> loadMaterial(UUID materialUUID) {
        return loadMaterialsPort.loadMaterial(materialUUID);
    }
}
