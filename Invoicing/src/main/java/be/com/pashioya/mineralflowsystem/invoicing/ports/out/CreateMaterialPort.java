package be.com.pashioya.mineralflowsystem.invoicing.ports.out;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Material;

public interface CreateMaterialPort {
    void materialCreated(Material material);
}
