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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MaterialCreatedAMQPPublisher implements CreateMaterialPort {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;



    @Override
    public void materialCreated(Material material) {
        var eventHeader = EventHeader.builder()
                .eventID(UUID.randomUUID())
                .eventCatalog(EventCatalog.MATERIAL_CREATED).build();
        var eventBody = new MaterialCreatedEvent(
                material.getUuid().uuid(),
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
