package com.example.payment.client;


import com.example.payment.dto.EmailMessageDTO;
import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import com.example.payment.exception.GenericServiceException;
import com.example.payment.model.AdvertPackage;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentLog;
import com.example.payment.repository.PaymentLogRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.UserRepository;
import com.example.payment.service.QueueService;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class StripeClient {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final QueueService queueService;
    @Autowired
    StripeClient(UserRepository userRepository, PaymentRepository paymentRepository, PaymentLogRepository paymentLogRepository, QueueService queueService) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.paymentLogRepository = paymentLogRepository;
        this.queueService = queueService;
        Stripe.apiKey = "sk_test_51KdhdHF64pjijg62TOiq6R0jHqThWZkbhwcASt7Uk9ccVyC0IpW7b7z7VojUy6IXaaJMh0s7aUO8FuRO3RNqBGkq00Yht38PKL";
    }

    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }

    public void chargeNewCard(String token, double amount,String email) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount));
        chargeParams.put("currency", "TRY");
        chargeParams.put("source", token);
        try{
            Charge charge = Charge.create(chargeParams);
            saveSuccessLog(charge,email);

        }catch (CardException c){
           saveErrorLog(c,email);
           log.info("Payment Declined " ,c);

        }
    }

    public void sendEmail(String email, String message,Payment payment){
        EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
        emailMessageDTO.setEmail(email);
        emailMessageDTO.setMessage(message);
        emailMessageDTO.setCurrency(payment.getCurrency());
        emailMessageDTO.setDuration(payment.getAdvertPackage().getDuration());
        emailMessageDTO.setAdvertNumber(payment.getAdvertPackage().getAdvertNumber());
        emailMessageDTO.setPrice(payment.getAdvertPackage().getPrice());
        queueService.sendMessage(emailMessageDTO);
    }

    public void saveSuccessLog(Charge charge, String email){
        Payment payment = new Payment();
        AdvertPackage advertPackage = new AdvertPackage();
        advertPackage.setId(1L);

        payment.setCurrency(Currency.TL);
        payment.setUser(userRepository.findByEmail(email).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"User not found")));
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setAdvertPackage(advertPackage);
        payment = paymentRepository.save(payment);
        sendEmail(email,"Your Payment Has Been Done",payment);

        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setPayment(payment);
        paymentLog.setPaymentDate(ZonedDateTime.now());
        paymentLog.setStatus(charge.getStatus());
        paymentLogRepository.save(paymentLog);
    }

    public void saveErrorLog(CardException c, String email){
        Payment payment = new Payment();
        AdvertPackage advertPackage = new AdvertPackage();
        advertPackage.setId(1L);
        payment.setCurrency(Currency.TL);
        payment.setUser(userRepository.findByEmail(email).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"User not found")));
        payment.setPaymentStatus(PaymentStatus.REJECTED);
        payment.setAdvertPackage(advertPackage);
        payment = paymentRepository.save(payment);

        sendEmail(email,"Your Payment Could Not Be Made",payment);
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setPayment(payment);
        paymentLog.setPaymentDate(ZonedDateTime.now());
        paymentLog.setStatus(c.getCode());
        paymentLog.setErrorMessage(c.getMessage());
        paymentLogRepository.save(paymentLog);
    }

    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

}
