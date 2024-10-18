package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
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
        WarehouseJPAEntity warehouseJPAEntity = warehouseRepository.findById(warehouseUUID).orElseThrow();
        return Optional.of(jpaToWarehouse(warehouseJPAEntity));
    }

    @Override
    public List<Warehouse> loadAllWarehouses() {
        return warehouseRepository.findAll().stream().map(this::jpaToWarehouse).toList();
    }

    private Warehouse jpaToWarehouse(WarehouseJPAEntity warehouseJPAEntity) {
        Warehouse warehouse = new Warehouse(warehouseJPAEntity);
        if (warehouseJPAEntity.getCustomerUUID() != null) {
            WarehouseCustomer customer = new WarehouseCustomer(customerProjectionRepository.findById(warehouseJPAEntity.getCustomerUUID()).orElseThrow());
            warehouse.setCustomer(customer);
        }
        if (warehouseJPAEntity.getMaterialUUID() != null) {
            Material material = new Material(materialRepository.findById(warehouseJPAEntity.getMaterialUUID()).orElseThrow());
            warehouse.setMaterial(material);
        }
        return warehouse;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        WarehouseJPAEntity warehouseJPAEntity = warehouseRepository.findById(warehouse.getWarehouseUUID().uuid()).orElseThrow();
        warehouseJPAEntity.setCapacity(warehouse.getCapacity());
        warehouseJPAEntity.setWarehouseNumber(warehouse.getWarehouseNumber());
        if (warehouse.getCustomer() != null) {
            warehouseJPAEntity.setCustomerUUID(warehouse.getCustomer().getWarehouseCustomerUUID().uuid());
        }
        if (warehouse.getMaterial() != null) {
            warehouseJPAEntity.setMaterialUUID(warehouse.getMaterial().getUuid().uuid());
        }
        warehouseRepository.save(warehouseJPAEntity);
    }
}
