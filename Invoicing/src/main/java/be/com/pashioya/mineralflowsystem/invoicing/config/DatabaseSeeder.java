package be.com.pashioya.mineralflowsystem.invoicing.config;



import be.com.pashioya.mineralflowsystem.invoicing.core.DefaultCreateCustomerUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerCommand;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final DefaultCreateCustomerUseCase defaultCreateCustomerUseCase;

    private void seed() {
        defaultCreateCustomerUseCase.createCustomer(new CreateCustomerCommand(
                "Paul ashioya",
                "Pashioyastraat 1, 2018 Antwerpen, Belgium",
                "john.ashioya@gmail.com",
                "BE1234567890"
        ));
        defaultCreateCustomerUseCase.createCustomer(new CreateCustomerCommand(
                "Rattadoom Ltd",
                "Molenbreek 1, 2000 Antwerpen, Belgium",
                "admin@rattadoom.eu",
                "BE1234567890"
        ));

        defaultCreateCustomerUseCase.createCustomer(new CreateCustomerCommand(
                "BoomChaka Ltd",
                "Trialszi 24, 200AB Rotterdam, Netherlands",
                " admin@boomchaka.eu",
                "NL1234567890"
        ));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seed();
    }
}
