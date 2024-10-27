package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.amqp;

import be.com.pashioya.mineralflowsystem.invoicing.adapters.config.RabbitMQModuleTopology;
import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import be.com.pashioya.mineralflowsystem.invoicing.ports.out.CreateCustomerPort;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerCreatedAMQPPublisher implements CreateCustomerPort {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(CustomerCreatedAMQPPublisher.class);

    @Override
    public void createCustomer(Customer customer) {

        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.CUSTOMER_CREATED).build();
        var eventBody = new CustomerCreatedEvent(
                customer.getCustomerUUID().uuid(),
                customer.getName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getVatNumber()
        );

        try {
            rabbitTemplate.convertAndSend(
                     "customer",RabbitMQModuleTopology.CUSTOMER_CREATED_ROUTING_KEY,
                    EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (Exception e) {
            logger.error("Error while sending customer created event to RabbitMQ", e);
        }
    }
}
