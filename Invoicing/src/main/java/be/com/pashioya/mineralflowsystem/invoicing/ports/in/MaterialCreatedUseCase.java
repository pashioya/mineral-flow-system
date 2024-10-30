package be.com.pashioya.mineralflowsystem.invoicing.ports.in;

import be.kdg.prog6.common.facades.invoicing.MaterialCreatedEvent;

public interface MaterialCreatedUseCase {
    void createMaterial(MaterialCreatedEvent materialEvent);
}
