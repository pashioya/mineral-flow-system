package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;


import be.com.pashioya.mineralflowsystem.invoicing.domain.Material;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateMaterialPort;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.LoadMaterialPort;
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
    public Optional<Material> loadMaterial(UUID materialUUID) {
        return materialRepository.findById(materialUUID).map(Material::new);
    }

    @Override
    public List<Material> loadAllMaterials() {
        return materialRepository.findAll().stream().map(Material::new).toList();
    }

    @Override
    public void materialCreated(Material material) {
        materialRepository.save(new MaterialJPAEntity(material));
    }
}
