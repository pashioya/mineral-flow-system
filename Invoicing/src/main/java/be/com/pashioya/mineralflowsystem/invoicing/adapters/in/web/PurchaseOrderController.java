package be.com.pashioya.mineralflowsystem.invoicing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreatePurchaseOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class PurchaseOrderController {
    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;

     @PostMapping("/purchase-orders")
    public ResponseEntity<HttpStatus> createPurchaseOrder(@RequestBody CreatePurchaseOrderCommand createPurchaseOrderCommand) {
         createPurchaseOrderUseCase.createPurchaseOrder(createPurchaseOrderCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
