package com.example.advert.repository;



import com.example.advert.model.Advert;
import com.sun.istack.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {
    Optional<Advert>  findByAdvertNo(Long advertNo);

    Page<Advert> findAll(@Nullable Specification<Advert> productSpecification, Pageable pageable);


}
