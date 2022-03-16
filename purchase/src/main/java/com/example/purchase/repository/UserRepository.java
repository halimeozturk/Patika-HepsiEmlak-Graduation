package com.example.purchase.repository;


import com.example.purchase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsById(Long id);

}
