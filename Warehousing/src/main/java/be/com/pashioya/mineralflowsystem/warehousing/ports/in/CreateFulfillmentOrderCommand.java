package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

import java.util.UUID;

public record CreateFulfillmentOrderCommand(
        UUID purchaseOrderUUID
) {
}
