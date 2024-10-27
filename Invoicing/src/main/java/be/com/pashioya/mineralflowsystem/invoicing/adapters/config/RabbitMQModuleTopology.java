package be.com.pashioya.mineralflowsystem.invoicing.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {

    public static final String CUSTOMER_CREATED_ROUTING_KEY = "customer.created";
    public static final String PURCHASE_ORDER_CREATED_ROUTING_KEY = "purchase-order.created";
    public static final String PURCHASE_ORDER_UPDATED_ROUTING_KEY = "purchase-order.updated";

    @Bean
    public FanoutExchange purchaseOrderFanoutExchange() {
        return new FanoutExchange("purchase-order");
    }

    @Bean
    public FanoutExchange customerFanoutExchange() {
        return new FanoutExchange("customer");
    }

    @Bean
    public Queue customerCreatedQueue() {
        return new Queue(CUSTOMER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Queue purchaseOrderCreatedQueue() {
        return new Queue(PURCHASE_ORDER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Queue purchaseOrderUpdatedQueue() {
        return new Queue(PURCHASE_ORDER_UPDATED_ROUTING_KEY);
    }

    @Bean
    public Binding customerCreatedBinding(FanoutExchange customerFanoutExchange, Queue customerCreatedQueue) {
        return BindingBuilder.bind(customerCreatedQueue).to(customerFanoutExchange);
    }

    @Bean
    public Binding purchaseOrderCreatedBinding(FanoutExchange purchaseOrderFanoutExchange, Queue purchaseOrderCreatedQueue) {
        return BindingBuilder.bind(purchaseOrderCreatedQueue).to(purchaseOrderFanoutExchange);
    }

    @Bean
    public Binding purchaseOrderUpdatedBinding(FanoutExchange purchaseOrderFanoutExchange, Queue purchaseOrderUpdatedQueue) {
        return BindingBuilder.bind(purchaseOrderUpdatedQueue).to(purchaseOrderFanoutExchange);
    }
}
