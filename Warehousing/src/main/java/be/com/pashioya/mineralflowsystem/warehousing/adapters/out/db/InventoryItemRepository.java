package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItemJPAEntity, UUID> {
}
