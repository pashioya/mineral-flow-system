package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWarehousePort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadWarehousePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class WarehouseDBAdapter implements CreateWarehousePort, LoadWarehousePort {

    private final WarehouseRepository warehouseRepository;

    @Override
    public void createWarehouse() {
        List<WarehouseJPAEntity> warehouses = warehouseRepository.findAll();
        WarehouseJPAEntity warehouse = new WarehouseJPAEntity();

        warehouse.setWarehouseNumber(warehouses.size() + 1);
        warehouse.setWarehouseUUID(UUID.randomUUID());
        warehouse.setCapacity(0);

        warehouseRepository.save(warehouse);
    }

    @Override
    public Optional<Warehouse> loadWarehouse(UUID warehouseUUID) {
        return warehouseRepository.findById(warehouseUUID).map( warehouseJPAEntity -> {
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouseUUID(new Warehouse.WareHouseUUID(warehouseJPAEntity.getWarehouseUUID()));
            warehouse.setWarehouseNumber(warehouseJPAEntity.getWarehouseNumber());
            warehouse.setCapacity(warehouseJPAEntity.getCapacity());
            return warehouse;
        });
    }

    @Override
    public List<Warehouse> loadAllWarehouses() {
        return warehouseRepository.findAll().stream().map( warehouseJPAEntity -> {
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouseUUID(new Warehouse.WareHouseUUID(warehouseJPAEntity.getWarehouseUUID()));
            warehouse.setWarehouseNumber(warehouseJPAEntity.getWarehouseNumber());
            warehouse.setCapacity(warehouseJPAEntity.getCapacity());
            return warehouse;
        }).toList();
    }
}
