package be.com.pashioya.mineralflowsystem.warehousing.ports.out;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;

public interface CreateMaterialPort {
    void materialCreated(Material material);
}
