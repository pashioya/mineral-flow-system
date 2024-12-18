package be.com.pashioya.mineralflowsystem.invoicing.domain;

import be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db.CustomerJPAEntity;
import be.com.pashioya.mineralflowsystem.invoicing.ports.in.CreateCustomerCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Customer {
    private CustomerUUID customerUUID;
    private String name;
    private String address;
    private String email;
    private String vatNumber;



    public record CustomerUUID(UUID uuid) {
    }

    public Customer(CustomerJPAEntity customerJPAEntity) {
        this.customerUUID = new CustomerUUID(customerJPAEntity.getCustomerUUID());
        this.name = customerJPAEntity.getName();
        this.address = customerJPAEntity.getAddress();
        this.email = customerJPAEntity.getEmail();
        this.vatNumber = customerJPAEntity.getVatNumber();
    }


    public Customer(CreateCustomerCommand command) {
        this.customerUUID = new CustomerUUID(UUID.randomUUID());
        this.name = command.name();
        this.address = command.address();
        this.email = command.email();
        this.vatNumber = command.vatNumber();
    }
}
