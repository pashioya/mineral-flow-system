package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto.FulfillmentOrderDTO;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CreateFulfillmentOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadFulfillmentOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadPurchaseOrderUseCase;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class FulfillmentOrderWebController {

    private final CreateFulfillmentOrderUseCase createFulfillmentOrderUseCase;
    private final LoadFulfillmentOrderUseCase loadFulfillmentOrderUseCase;
    private final LoadPurchaseOrderUseCase loadPurchaseOrderUseCase;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(FulfillmentOrderWebController.class);


    @PostMapping("/fulfillment-orders")
    public ResponseEntity<HttpStatus> createFulfillmentOrder(@RequestBody CreateFulfillmentOrderCommand createFulfillmentOrderCommand) {

        if(loadPurchaseOrderUseCase.loadPurchaseOrder(createFulfillmentOrderCommand.purchaseOrderUUID()).isEmpty())
        {
            logger.error("Purchase Order with UUID {} does not exist", createFulfillmentOrderCommand.purchaseOrderUUID());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        createFulfillmentOrderUseCase.createFulfillmentOrder(createFulfillmentOrderCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/fulfillment-orders")
    public ResponseEntity<List<FulfillmentOrderDTO>> loadAllFulfillmentOrders() {
        return new ResponseEntity<>(loadFulfillmentOrderUseCase.
                loadAllFulfillmentOrders().stream()
                .map(FulfillmentOrderDTO::new).toList(), HttpStatus.OK);
    }
}
