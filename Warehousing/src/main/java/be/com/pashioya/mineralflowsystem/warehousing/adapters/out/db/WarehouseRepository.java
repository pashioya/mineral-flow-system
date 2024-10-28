package be.com.pashioya.mineralflowsystem.warehousing.adapters.out.db;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseJPAEntity, UUID> {

    List<WarehouseJPAEntity> findByCustomerUUID(UUID warehouseCustomerUUID);
}
