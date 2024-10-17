package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadWarehouseUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadWarehousePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultLoadWarehouseUseCase implements LoadWarehouseUseCase {

    private final LoadWarehousePort loadWarehousePort;

    @Override
    public Optional<Warehouse> loadWarehouse(UUID warehouseUUID) {
        return loadWarehousePort.loadWarehouse(warehouseUUID);
    }

    @Override
    public List<Warehouse> loadAllWarehouses() {
        return loadWarehousePort.loadAllWarehouses();
    }
}
