package be.com.pashioya.mineralflowsystem.warehousing.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {

    public static final String CUSTOMER_CREATED_ROUTING_KEY = "customer.created";
    public static final String PURCHASE_ORDER_CREATED_ROUTING_KEY = "purchase-order.created";
    public static final String PURCHASE_ORDER_UPDATED_ROUTING_KEY = "purchase-order.updated";

    @Bean
    public FanoutExchange customerFanoutExchange() {
        return new FanoutExchange("customer");
    }

    @Bean
    public FanoutExchange purchaseOrderExchange() {
        return new FanoutExchange("purchase-order");
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
    public Binding purchaseOrderCreatedBinding(FanoutExchange purchaseOrderExchange, Queue purchaseOrderCreatedQueue) {
        return BindingBuilder.bind(purchaseOrderCreatedQueue).to(purchaseOrderExchange);
    }

    @Bean
    public Binding purchaseOrderUpdatedBinding(FanoutExchange purchaseOrderExchange, Queue purchaseOrderUpdatedQueue) {
        return BindingBuilder.bind(purchaseOrderUpdatedQueue).to(purchaseOrderExchange);
    }
}
