package com.gustavoreinaldi.tokiomarine.repositories;

import com.gustavoreinaldi.tokiomarine.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, UUID> {
    @Query("SELECT transfers FROM TransferEntity transfers ORDER BY transfers.scheduledDate ASC")
    List<TransferEntity> getAllOrderByScheduledDate();
}
