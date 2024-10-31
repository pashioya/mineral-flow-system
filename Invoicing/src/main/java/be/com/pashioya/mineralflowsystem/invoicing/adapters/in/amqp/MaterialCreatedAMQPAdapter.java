package be.com.pashioya.mineralflowsystem.invoicing.adapters.in.amqp;

import be.com.pashioya.mineralflowsystem.invoicing.adapters.in.MaterialEventHandler;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.MaterialCreatedUseCase;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.facades.invoicing.MaterialCreatedEvent;
import be.kdg.prog6.common.facades.invoicing.MaterialEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MaterialCreatedAMQPAdapter implements MaterialEventHandler<MaterialCreatedEvent> {

    MaterialCreatedUseCase materialCreatedUseCase;
    private final Logger logger = LoggerFactory.getLogger(MaterialCreatedAMQPAdapter.class);


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.MATERIAL_CREATED == eventCatalog;
    }

    @Override
    public MaterialCreatedEvent map(String eventBody) {
        try {
            return new ObjectMapper().readValue(eventBody, MaterialCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to map event body to PurchaseOrderUpdatedEvent", e);
        }
    }

    @Override
    public void handle(MaterialEvent materialEvent) {
        logger.info("Creating Material with UUID: {}", ((MaterialCreatedEvent) materialEvent).materialUUID());
        materialCreatedUseCase.createMaterial((MaterialCreatedEvent) materialEvent);
    }
}
