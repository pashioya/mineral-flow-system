package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "warehouse_customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerProjectionJPAEntity {
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
}
