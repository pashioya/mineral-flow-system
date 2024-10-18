package be.com.pashioya.mineralflowsystem.warehousing.ports.in;



import java.time.LocalDateTime;
import java.util.UUID;

public record CreateInventoryItemCommand(UUID customerUUID, UUID materialUUID, UUID warehouseUUID, int quantity, LocalDateTime dateReceived) {
}