package be.com.pashioya.mineralflowsystem.warehousing.core;

import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CustomerCreatedUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWareHouseCustomerPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadMaterialPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.LoadWarehousePort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultCustomerCreatedUseCase implements CustomerCreatedUseCase {

    private final CreateWareHouseCustomerPort createWareHouseCustomerPort;
    private final UpdateWarehousePort updateWarehousePort;
    private final LoadWarehousePort loadWarehousePort;
    private final LoadMaterialPort loadMaterialPort;
    private final Logger logger = LoggerFactory.getLogger(DefaultCustomerCreatedUseCase.class);

    @Override
public void createWarehouseCustomer(CustomerCreatedEvent customerEvent) {
    WarehouseCustomer newCustomer = new WarehouseCustomer(customerEvent);
    createWareHouseCustomerPort.createWareHouseCustomer(newCustomer);

    logger.info("Created new warehouse customer {}", newCustomer.getName());

    List<Material> materials = loadMaterialPort.loadAllMaterials();
    logger.info("Found materials: {}", materials);


    List<Warehouse> emptyWarehouses = new java.util.ArrayList<>(loadWarehousePort.loadAllWarehouses()
            .stream()
            .filter(warehouse -> warehouse.getWarehouseCustomerUUID() == null)
            .toList());

    logger.info("Found {} empty warehouses", emptyWarehouses.size());
    logger.info("Found {} materials", materials.size());

    if (emptyWarehouses.size() < materials.size()) {
        throw new RuntimeException("Not enough empty warehouses to assign each material to the new customer.");
    }

    for (Material material : materials){
        Warehouse warehouse = emptyWarehouses.getFirst();
        warehouse.setWarehouseCustomerUUID(new WarehouseCustomer.WarehouseCustomerUUID(customerEvent.customerUUID()));
        warehouse.setMaterialUUID(material.getUuid());

        logger.info("Assigning warehouse {} to customer {} for material {}", warehouse.getWarehouseUUID(), customerEvent.customerUUID(), material.getUuid());
        updateWarehousePort.updateWarehouse(warehouse);
        emptyWarehouses.remove(warehouse);
    }
}
}
