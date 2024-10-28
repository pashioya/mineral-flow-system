package be.com.pashioya.mineralflowsystem.invoicing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.LoadCustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PurchaseOrderController {
    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;
    private final LoadCustomerPort loadCustomerPort;

     @PostMapping("/purchase-orders")
    public ResponseEntity<HttpStatus> createPurchaseOrder(@RequestBody CreatePurchaseOrderCommand createPurchaseOrderCommand) {

//         TODO: REMOVE THIS TEMPORARY FIX MEANT FOR DATABASE SEEDING

         if (createPurchaseOrderCommand.customerUUID() == null){
             List<Customer> customers = loadCustomerPort.loadAllCustomers();

             createPurchaseOrderUseCase.createPurchaseOrder(new CreatePurchaseOrderCommand(
                        customers.get(2).getCustomerUUID().uuid(),
                        createPurchaseOrderCommand.orderNumber(),
                        createPurchaseOrderCommand.address(),
                        createPurchaseOrderCommand.deliveryDate(),
                        createPurchaseOrderCommand.orderItems()
             ));
         }

         createPurchaseOrderUseCase.createPurchaseOrder(createPurchaseOrderCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
