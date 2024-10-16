package be.com.pashioya.mineralflowsystem.invoicing.domain;

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
}
