package com.example.purchase.repository;

import com.example.purchase.model.AdvertPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertPackageRepository extends JpaRepository<AdvertPackage,Long> {
    boolean existsById(Long id);
}
