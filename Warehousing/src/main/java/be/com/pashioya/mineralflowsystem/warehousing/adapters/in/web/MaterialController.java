package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.web.dto.MaterialDTO;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.LoadMaterialsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class MaterialController {

    private final LoadMaterialsUseCase loadMaterialUseCase;

    @GetMapping("/materials")
    public ResponseEntity<List<MaterialDTO>> loadAllMaterials() {
        return new ResponseEntity<>(loadMaterialUseCase.loadAllMaterials().stream().map(MaterialDTO::new).toList(), HttpStatus.OK);
    }
}
