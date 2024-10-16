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

        MaterialJPAEntity materialJPAEntity = new MaterialJPAEntity();
        materialJPAEntity.setMaterialUUID(material.getUuid().uuid());
        materialJPAEntity.setName(material.getName());
        materialJPAEntity.setDescription(material.getDescription());
        materialJPAEntity.setPrice(material.getPrice());
        materialJPAEntity.setStoragePrice(material.getStoragePrice());
        materialRepository.save(materialJPAEntity);
    }

    @Override
    public Optional<Material> loadMaterial(UUID materialUUID) {
        return materialRepository.findById(materialUUID)
                .map(materialJPAEntity ->
                        new Material(
                                new Material.MaterialUUID(materialJPAEntity.getMaterialUUID()),
                                materialJPAEntity.getName(), materialJPAEntity.getDescription(),
                                materialJPAEntity.getPrice(), materialJPAEntity.getStoragePrice())
                );
    }

    @Override
    public List<Material> loadAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialJPAEntity ->
                        new Material(
                                new Material.MaterialUUID(materialJPAEntity.getMaterialUUID()),
                                materialJPAEntity.getName(), materialJPAEntity.getDescription(),
                                materialJPAEntity.getPrice(), materialJPAEntity.getStoragePrice())
                )
                .toList();
    }
}
