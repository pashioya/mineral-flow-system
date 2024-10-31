package be.com.pashioya.mineralflowsystem.invoicing.config;



import be.com.pashioya.mineralflowsystem.invoicing.core.DefaultCreateCustomerUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.core.DefaultCreatePurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.domain.PurchaseOrder;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderCommand;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final DefaultCreateCustomerUseCase defaultCreateCustomerUseCase;
    private final DefaultCreatePurchaseOrderUseCase defaultCreatePurchaseOrderUseCase;

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

//        TODO: Create purchase orders using frontend form, random uuids cant work
//        defaultCreatePurchaseOrderUseCase.createPurchaseOrder(new CreatePurchaseOrderCommand(
//                UUID.randomUUID(),
//                "PO-2021-0001",
//                "Pashioyastraat 1, 2018 Antwerpen, Belgium",
//                LocalDateTime.of(2025, 1, 1, 0, 0),
//                List.of(
//                        new PurchaseOrder.OrderItem(UUID.randomUUID(), 1, 100.0),
//                        new PurchaseOrder.OrderItem(UUID.randomUUID(), 2, 200.0),
//                        new PurchaseOrder.OrderItem(UUID.randomUUID(), 3, 300.0)
//                )
//        ));
    }

    @Override
    public void run(ApplicationArguments args)  {
        seed();
    }
}
