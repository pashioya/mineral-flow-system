package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateMaterialCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateMaterialUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateMaterialPort;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DefaultCreateMaterialUseCase implements CreateMaterialUseCase {

    private final List<CreateMaterialPort> createMaterialPorts;

    @Override
    public void createMaterial(CreateMaterialCommand command) {

        Material material = new Material(
                command.name(),
                command.description(),
                command.price(),
                command.storagePrice());

        createMaterialPorts.forEach(createMaterialPort ->
        createMaterialPort.materialCreated(material));
    }
}

