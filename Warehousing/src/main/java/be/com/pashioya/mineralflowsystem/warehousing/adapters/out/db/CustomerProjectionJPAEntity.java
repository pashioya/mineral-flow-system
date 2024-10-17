package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Entity
@Table(name = "warehouse_customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerProjectionJPAEntity {
    @Id
    private UUID customerUUID;
    private String name;
    private String address;
    private String email;
}
