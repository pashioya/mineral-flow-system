package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto.PurchaseOrderDTO;
import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadPurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class PurchaseOrderController {

    private final LoadPurchaseOrderUseCase loadPurchaseOrderUseCase;
    private final UpdatePurchaseOrderUseCase updatePurchaseOrderUseCase;

    @GetMapping("/purchase-orders")
    public ResponseEntity<List<PurchaseOrderDTO>> loadAllPurchaseOrders() {
        return new ResponseEntity<>(loadPurchaseOrderUseCase.loadAllPurchaseOrders().stream().map(PurchaseOrderDTO::new).toList(), HttpStatus.OK);
    }

    @GetMapping("/purchase-orders/{purchaseOrderUUID}")
    public ResponseEntity<PurchaseOrderDTO> loadPurchaseOrder(@PathVariable UUID purchaseOrderUUID){
        return new ResponseEntity<>(loadPurchaseOrderUseCase.loadPurchaseOrder(purchaseOrderUUID).map(PurchaseOrderDTO::new).orElse(null), HttpStatus.OK);
    }

    @PutMapping("/purchase-orders/{purchaseOrderUUID}")
    public ResponseEntity<HttpStatus> updatePurchaseOrder(@PathVariable UUID purchaseOrderUUID, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
            UpdatePurchaseOrderCommand updatePurchaseOrderCommand = new UpdatePurchaseOrderCommand(
                    purchaseOrderUUID,
                    purchaseOrderDTO.getAddress(),
                    purchaseOrderDTO.getDeliveryDate(),
                    purchaseOrderDTO.getOrderStatus(),
                    purchaseOrderDTO.getOrderItems().stream().map(ActivePurchaseOrder.PurchaseOrderItem::new).toList());
            updatePurchaseOrderUseCase.updatePurchaseOrder(updatePurchaseOrderCommand);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
