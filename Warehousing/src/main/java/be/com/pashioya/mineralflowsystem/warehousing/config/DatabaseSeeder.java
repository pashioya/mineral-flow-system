package be.com.pashioya.mineralflowsystem.warehousing.config;


import be.com.pashioya.mineralflowsystem.warehousing.core.DefaultCreateMaterialUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateMaterialCommand;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final DefaultCreateMaterialUseCase defaultCreateMaterialUseCase;


    public void seed() {
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
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seed();
    }
}
