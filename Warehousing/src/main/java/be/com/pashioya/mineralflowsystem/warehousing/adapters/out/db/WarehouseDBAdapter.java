package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWarehousePort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadWarehousePort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateWarehousePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class WarehouseDBAdapter implements CreateWarehousePort, LoadWarehousePort, UpdateWarehousePort {

    private final WarehouseRepository warehouseRepository;
    private final CustomerProjectionRepository customerProjectionRepository;
    private final MaterialRepository materialRepository;

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
        return  warehouseRepository.findById(warehouseUUID).map(Warehouse::new);
    }

    @Override
    public List<Warehouse> loadAllWarehouses() {
        return warehouseRepository.findAll().stream().map(Warehouse::new).toList();
    }

    @Override
    public List<Warehouse> loadByWarehouseCustomerUUID(WarehouseCustomer.WarehouseCustomerUUID warehouseCustomerUUID) {
        return warehouseRepository.findByCustomerUUID(warehouseCustomerUUID.uuid()).stream().map(Warehouse::new).toList();
    }


    @Override
    public void updateWarehouse(Warehouse warehouse) {
        WarehouseJPAEntity warehouseJPAEntity = warehouseRepository.findById(warehouse.getWarehouseUUID().uuid()).orElseThrow();
        warehouseJPAEntity.setCapacity(warehouse.getCapacity());
        warehouseJPAEntity.setWarehouseNumber(warehouse.getWarehouseNumber());
        if (warehouse.getWarehouseCustomerUUID() != null) {
            warehouseJPAEntity.setCustomerUUID(warehouse.getWarehouseCustomerUUID().uuid());
        }
        if (warehouse.getMaterialUUID() != null) {
            warehouseJPAEntity.setMaterialUUID(warehouse.getMaterialUUID().uuid());
        }
        warehouseRepository.save(warehouseJPAEntity);
    }
}
