package be.kdg.prog6.common.facades.invoicing;

import java.util.UUID;

public record CustomerCreatedEvent(
        UUID customerUUID,
        String name,
        String address,
        String email,
        String vatNumber
) {
}
