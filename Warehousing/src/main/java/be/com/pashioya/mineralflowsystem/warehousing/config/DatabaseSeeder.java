package be.com.pashioya.mineralflowsystem.warehousing.config;


import be.com.pashioya.mineralflowsystem.warehousing.core.*;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Warehouse;
import be.com.pashioya.mineralflowsystem.warehousing.domain.WarehouseCustomer;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateMaterialCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateWarehouseCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateWareHouseCustomerPort;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateWarehousePort;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final DefaultCreateMaterialUseCase defaultCreateMaterialUseCase;
    private final DefaultCreateWarehouseUseCase defaultCreateWarehouseUseCase;
    private final DefaultLoadWarehouseUseCase defaultLoadWarehouseUseCase;
    private final DefaultCreateInventoryItemUseCase defaultCreateInventoryItemUseCase;
    private final DefaultLoadMaterialsUseCase defaultLoadMaterialUseCase;
    private final CreateWareHouseCustomerPort createWareHouseCustomerPort;
    private final UpdateWarehousePort updateWarehousePort;

    public void seed() {

        for (int i = 0; i < 25; i++){
            defaultCreateWarehouseUseCase.createWarehouse(new CreateWarehouseCommand());
        }

        defaultCreateMaterialUseCase.createMaterial(
                new CreateMaterialCommand(
                        "Gypsum",
                        "Gypsum is a soft sulfate mineral composed of calcium sulfate dihydrate. It is commonly used in the construction industry for producing plaster, plasterboard, and cement. Gypsum is also used in agriculture as a soil conditioner and fertilizer.",
                        13.0,
                        1.0));
        defaultCreateMaterialUseCase.createMaterial(
                new CreateMaterialCommand(
                        "Iron Ore",
                        "Iron ore is a naturally occurring mineral from which iron is extracted. It is a crucial raw material in the production of steel, which is used extensively in construction, manufacturing, and transportation industries.",
                        110.0,
                        5.0));
        defaultCreateMaterialUseCase.createMaterial(
                new CreateMaterialCommand(
                        "Cement",
                        "Cement is a binder substance used in construction that sets, hardens, and adheres to other materials to bind them together. It is a key ingredient in concrete, mortar, and stucco.",
                        95.0,
                        3.0));
        defaultCreateMaterialUseCase.createMaterial(
                new CreateMaterialCommand(
                        "Petcoke",
                        "Petcoke is a carbon-rich solid material derived from oil refining. It is used as a fuel in power generation, cement kilns, and other industrial processes due to its high calorific value.",
                        210.0,
                        10.0));
        defaultCreateMaterialUseCase.createMaterial(
                new CreateMaterialCommand(
                        "Slag",
                        "Slag is a byproduct of the smelting process used to produce metals from their ores. It is used in construction as an aggregate in concrete, road construction, and as a raw material in cement production.",
                        160.0,
                        7.0));

        List<Warehouse> warehouses = defaultLoadWarehouseUseCase.loadAllWarehouses();
        List<Material> materials = defaultLoadMaterialUseCase.loadAllMaterials();

        UUID customerUUID =  UUID.randomUUID();

        createWareHouseCustomerPort.createWareHouseCustomer(
                new WarehouseCustomer(
                        new WarehouseCustomer.WarehouseCustomerUUID(customerUUID),
                        "TEST CUSTOMER",
                        "123 Main St, Anytown, USA",
                        "john.doe@gmail.com"
                )
        );

        for (int i = 0; i < materials.size(); i++){
            warehouses.get(i).setWarehouseCustomerUUID(new WarehouseCustomer.WarehouseCustomerUUID(customerUUID));
            warehouses.get(i).setMaterialUUID(materials.get(i).getUuid());
            updateWarehousePort.updateWarehouse(warehouses.get(i));
        }

//        for (int i = 0; i < 3; i++){
//            int randomIndex = (int) (Math.random() * warehouses.size());
//            Warehouse warehouse = warehouses.get(randomIndex);
//            defaultCreateInventoryItemUseCase.createInventoryItem(
//                    new CreateInventoryItemCommand(
//                            customerUUID,
//                            materials.get(i).getUuid().uuid(),
//                            warehouse.getWarehouseUUID().uuid(),
//                            (int) (Math.random() * 100),
//                            LocalDateTime.now()
//                    )
//            );
//        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seed();
    }
}
