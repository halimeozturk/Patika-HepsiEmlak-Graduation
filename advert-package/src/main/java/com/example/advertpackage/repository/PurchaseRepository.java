package com.example.advertpackage.repository;

import com.example.advertpackage.model.Purchase;
import com.example.advertpackage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
