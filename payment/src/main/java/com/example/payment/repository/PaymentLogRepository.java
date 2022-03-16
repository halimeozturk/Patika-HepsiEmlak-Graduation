package com.example.payment.repository;

import com.example.payment.model.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentLogRepository extends JpaRepository<PaymentLog,Long> {
}
