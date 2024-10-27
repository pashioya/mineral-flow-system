package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto.PurchaseOrderDTO;
import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadPurchaseOrderUseCase;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderCommand;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.UpdatePurchaseOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class PurchaseOrderController {

    private final LoadPurchaseOrderUseCase loadPurchaseOrderUseCase;
    private final UpdatePurchaseOrderUseCase updatePurchaseOrderUseCase;

    @GetMapping("/purchase-orders")
    public List<PurchaseOrderDTO> loadAllPurchaseOrders() {
        return loadPurchaseOrderUseCase.loadAllPurchaseOrders().stream().map(PurchaseOrderDTO::new).toList();
    }

    @GetMapping("/purchase-orders/{purchaseOrderUUID}")
    public PurchaseOrderDTO loadPurchaseOrder(@PathVariable UUID purchaseOrderUUID){
        return loadPurchaseOrderUseCase.loadPurchaseOrder(purchaseOrderUUID).map(PurchaseOrderDTO::new).orElseThrow();
    }

    @PutMapping("/purchase-orders/{purchaseOrderUUID}")
    public void updatePurchaseOrder(@PathVariable UUID purchaseOrderUUID, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
            UpdatePurchaseOrderCommand updatePurchaseOrderCommand = new UpdatePurchaseOrderCommand(
                    purchaseOrderUUID,
                    purchaseOrderDTO.getAddress(),
                    purchaseOrderDTO.getDeliveryDate(),
                    purchaseOrderDTO.getOrderStatus(),
                    purchaseOrderDTO.getOrderItems().stream().map(ActivePurchaseOrder.OrderItem::new).toList());
            updatePurchaseOrderUseCase.updatePurchaseOrder(updatePurchaseOrderCommand);
    }
}
