package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateWarehouseCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateWarehouseUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWarehousePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultCreateWarehouseUseCase implements CreateWarehouseUseCase
{

    private final CreateWarehousePort createWarehousePort;

    @Override
    public void createWarehouse(CreateWarehouseCommand command) {
        createWarehousePort.createWarehouse();
    }
}
