package be.com.pashioya.mineralflowsystem.invoicing.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {

    public static final String CUSTOMER_CREATED_ROUTING_KEY = "customer.created";
    public static final String PURCHASE_ORDER_CREATED_ROUTING_KEY = "purchaseOrder.created";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("invoicing");
    }

    @Bean
    public Queue customerCreatedQueue() {
        return new Queue("customer.created");
    }

    @Bean
    public Queue purchaseOrderCreatedQueue() {
        return new Queue("purchaseOrder.created");
    }

    @Bean
    public Binding customerCreatedBinding(TopicExchange exchange, Queue customerCreatedQueue) {
        return BindingBuilder.bind(customerCreatedQueue).to(exchange).with(CUSTOMER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding purchaseOrderCreatedBinding(TopicExchange exchange, Queue purchaseOrderCreatedQueue) {
        return BindingBuilder.bind(purchaseOrderCreatedQueue).to(exchange).with(PURCHASE_ORDER_CREATED_ROUTING_KEY);
    }
}

