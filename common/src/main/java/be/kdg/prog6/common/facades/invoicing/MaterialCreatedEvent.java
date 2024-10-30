package be.kdg.prog6.common.facades.invoicing;


import java.util.UUID;

public record MaterialCreatedEvent
        (UUID materialUUID, String name, String description, double price, double storagePrice
) implements MaterialEvent{
}
