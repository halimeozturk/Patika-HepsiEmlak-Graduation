package com.example.payment.repository;

import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PaymentLogRepositoryTest {
    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @Test
    void create(){
        PaymentLog paymentLog = paymentLogRepository.save(preparePaymentLog());
        assertNotNull(paymentLog);
    }

    private PaymentLog preparePaymentLog(){
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setId(2L);
        paymentLog.setPayment(null);
        paymentLog.setPaymentDate(ZonedDateTime.now());
        paymentLog.setStatus("COMPLETED");
        return paymentLog;
    }
}

