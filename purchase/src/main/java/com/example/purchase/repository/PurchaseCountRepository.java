package com.example.purchase.repository;

import com.example.purchase.model.PurchaseCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseCountRepository  extends JpaRepository<PurchaseCount,Long> {
    List<PurchaseCount> findAllByUserId(UUID userId);



    @Query("select pc from PurchaseCount pc where pc.userId =?1 and pc.endDate > current_date ")
    PurchaseCount getAllCurrentPurchaseCount(UUID userId);

}
