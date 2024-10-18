package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import be.com.pashioya.mineralflowsystem.invoicing.domain.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerJPAEntity {
    @Id
    private UUID customerUUID;
    private String name;
    private String address;
    private String email;
    private String vatNumber;

    public CustomerJPAEntity(Customer customer) {
        this.customerUUID = customer.getCustomerUUID().uuid();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.vatNumber = customer.getVatNumber();
    }
}
