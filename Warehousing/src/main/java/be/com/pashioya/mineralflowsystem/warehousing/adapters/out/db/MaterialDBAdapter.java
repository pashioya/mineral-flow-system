package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateMaterialPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadMaterialPort;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MaterialDBAdapter implements CreateMaterialPort, LoadMaterialPort {

    private final MaterialRepository materialRepository;

    @Override
    public void materialCreated(Material material) {
        materialRepository.save(new MaterialJPAEntity(material));
    }

    @Override
    public Optional<Material> loadMaterial(UUID materialUUID) {
        return materialRepository.findById(materialUUID).map(Material::new);
    }

    @Override
    public List<Material> loadAllMaterials() {
        return materialRepository.findAll().stream().map(Material::new).toList();
    }
}
