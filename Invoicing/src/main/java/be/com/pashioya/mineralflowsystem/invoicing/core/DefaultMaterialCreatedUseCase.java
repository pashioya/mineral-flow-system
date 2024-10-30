package be.com.pashioya.mineralflowsystem.invoicing.core;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Material;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.MaterialCreatedUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateMaterialPort;
import be.kdg.prog6.common.facades.invoicing.MaterialCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultMaterialCreatedUseCase implements MaterialCreatedUseCase {

    private final CreateMaterialPort createMaterialPort;

    @Override
    public void createMaterial(MaterialCreatedEvent materialEvent) {
        System.out.println("DefaultMaterialCreatedUseCase.createMaterial: " + materialEvent);
        createMaterialPort.materialCreated(
            new Material(
                new Material.MaterialUUID(materialEvent.materialUUID()),
                materialEvent.name(),
                materialEvent.description(),
                materialEvent.price(),
                materialEvent.storagePrice()
            )
        );
    }
}
