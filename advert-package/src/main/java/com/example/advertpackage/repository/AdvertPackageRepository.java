package com.example.advertpackage.repository;

import com.example.advertpackage.model.AdvertPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertPackageRepository extends JpaRepository<AdvertPackage,Long> {
}
