package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateMaterialCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateMaterialUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateMaterialPort;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCreateMaterialUseCase implements CreateMaterialUseCase {

    private final CreateMaterialPort createMaterialPort;

    @Override
    public void createMaterial(CreateMaterialCommand command) {
        createMaterialPort.materialCreated(new Material(new Material.MaterialUUID(UUID.randomUUID()),command.name(), command.description(), command.price(), command.storagePrice()));
    }

}
