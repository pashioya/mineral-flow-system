package be.com.pashioya.mineralflowsystem.invoicing.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerJPAEntity, UUID> {
}