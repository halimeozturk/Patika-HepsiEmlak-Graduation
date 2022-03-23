package com.example.payment.repository;

import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import com.example.payment.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void create(){
        Payment payment = paymentRepository.save(preparePayment());
        assertNotNull(payment);
    }


    private Payment preparePayment(){
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setCurrency(Currency.TL);
        payment.setAdvertPackageId(1L);
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        return payment;
    }
}

