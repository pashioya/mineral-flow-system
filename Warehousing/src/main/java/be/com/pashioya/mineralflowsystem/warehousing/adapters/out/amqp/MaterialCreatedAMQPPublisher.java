package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.amqp;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.config.RabbitMQModuleTopology;
import be.com.pashioya.mineralflowsystem.warehousing.domain.Material;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.CreateMaterialPort;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.MaterialCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MaterialCreatedAMQPPublisher implements CreateMaterialPort {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(MaterialCreatedAMQPPublisher.class);


    @Override
    public void materialCreated(Material material) {
        var eventHeader = EventHeader.builder()
                .eventID(UUID.randomUUID())
                .eventCatalog(EventCatalog.MATERIAL_CREATED).build();

        logger.info("Sending MaterialCreatedEvent for material with UUID: {}", material.getMaterialUUID().uuid());
        logger.info("eventHeader: {}", eventHeader);
        var eventBody = new MaterialCreatedEvent(
                material.getMaterialUUID().uuid(),
                material.getName(),
                material.getDescription(),
                material.getPrice(),
                material.getStoragePrice()
        );

        try {
             rabbitTemplate.convertAndSend("material", RabbitMQModuleTopology.MATERIAL_CREATED_ROUTING_KEY,
                                         EventMessage.builder()
                                                 .eventHeader(eventHeader)
                                                 .eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
