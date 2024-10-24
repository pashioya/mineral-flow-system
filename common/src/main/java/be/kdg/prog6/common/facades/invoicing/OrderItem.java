package be.kdg.prog6.common.facades.invoicing;

import java.util.UUID;

public record OrderItem(
            UUID orderItemUUID,
            UUID purchaseOrderUUID,
            UUID materialUUID,
            int quantity,
            double price
    ) {
    }