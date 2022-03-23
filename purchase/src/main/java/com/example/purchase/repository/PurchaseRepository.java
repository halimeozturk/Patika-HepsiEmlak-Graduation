package com.example.purchase.repository;

import com.example.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    Optional<List<Purchase>> findByUserId(UUID userId);
}
