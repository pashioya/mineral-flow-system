package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto.PurchaseOrderDTO;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadPurchaseOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class PurchaseOrderController {

    private final LoadPurchaseOrderUseCase loadPurchaseOrderUseCase;

    @GetMapping("/purchase-orders")
    public List<PurchaseOrderDTO> loadAllPurchaseOrders() {
        return loadPurchaseOrderUseCase.loadAllPurchaseOrders().stream().map(PurchaseOrderDTO::new).toList();
    }
}
