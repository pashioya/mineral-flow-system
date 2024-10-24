package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.amqp;

import be.com.pashioya.mineralflowsystem.warehousing.domain.ActivePurchaseOrder;
import be.com.pashioya.mineralflowsystem.warehousing.ports.out.UpdateActivePurchaseOrderPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchaseOrderUpdatedAMQPAdapter implements UpdateActivePurchaseOrderPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void updateActivePurchaseOrder(ActivePurchaseOrder activePurchaseOrder) {
//        TODO: Implement this method
        try {
            rabbitTemplate.convertAndSend("purchase-order", "purchase-order.updated", objectMapper.writeValueAsString(activePurchaseOrder));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
