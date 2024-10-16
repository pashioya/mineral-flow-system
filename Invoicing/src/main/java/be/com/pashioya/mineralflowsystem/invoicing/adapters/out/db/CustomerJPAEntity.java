package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private UUID customerUUID;
    @Setter
    private String name;
    @Setter
    private String address;
    @Setter
    private String email;
    @Setter
    private String vatNumber;
}
