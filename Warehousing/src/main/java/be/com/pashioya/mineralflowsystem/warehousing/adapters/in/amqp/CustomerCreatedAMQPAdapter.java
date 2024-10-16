package be.com.pashioya.mineralflowsystem.warehousing.adapters.in.amqp;

import be.com.pashioya.mineralflowsystem.warehousing.adapters.in.CustomerEventHandler;
import be.com.pashioya.mineralflowsystem.warehousing.ports.in.CustomerCreatedUseCase;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.invoicing.CustomerCreatedEvent;
import be.kdg.prog6.common.facades.invoicing.CustomerEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CustomerCreatedAMQPAdapter implements CustomerEventHandler<CustomerCreatedEvent> {

    private final ObjectMapper objectMapper;
    private final CustomerCreatedUseCase customerCreatedUseCase;
    private final Logger logger = LoggerFactory.getLogger(CustomerCreatedAMQPAdapter.class);


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.CUSTOMER_CREATED == eventCatalog;
    }

    @Override
    public CustomerEventHandler<CustomerCreatedEvent> receive(EventMessage eventMessage) {
        return CustomerEventHandler.super.receive(eventMessage);
    }

    @Override
    public CustomerCreatedEvent map(String eventBody) {
        logger.info("Mapping event body to CustomerCreatedEvent");
        try {
            return objectMapper.readValue(eventBody, CustomerCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to map event body to CustomerCreatedEvent", e);
        }
    }

    @Override
    public void handle(CustomerEvent attractionEventBody) {
        logger.info("Handling CustomerCreatedEvent");
        customerCreatedUseCase.createWarehouseCustomer((CustomerCreatedEvent) attractionEventBody);
    }


}
