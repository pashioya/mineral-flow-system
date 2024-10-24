package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto.WarehouseCustomerDTO;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadWarehouseCustomerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class WarehouseCustomerController {

    private final LoadWarehouseCustomerUseCase loadWarehouseCustomerUseCase;

    @GetMapping("/warehouse-customers")
    public List<WarehouseCustomerDTO> loadAllWarehouseCustomers() {
        return loadWarehouseCustomerUseCase.loadWarehouseCustomers().stream().map(WarehouseCustomerDTO::new).toList();
    }
}
