package be.com.pashioya.mineralflowsystem.warehousing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class InventoryItem {
    private InventoryItemUUID uuid;
    private WarehouseCustomer customer;
    private Material material;
    private double quantity;
    private LocalDateTime dateReceived;

    public record InventoryItemUUID(UUID uuid) {
    }
}
