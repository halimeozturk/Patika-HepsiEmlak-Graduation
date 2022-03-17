package com.example.purchase.repository;

import com.example.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    Optional<Purchase> findByUserId(Long userId);
}
