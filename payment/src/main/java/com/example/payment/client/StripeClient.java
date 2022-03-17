package com.example.payment.client;


import com.example.payment.dto.AdvertPackageDTO;
import com.example.payment.dto.EmailMessageDTO;
import com.example.payment.dto.PurchaseDTO;
import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import com.example.payment.enums.PurchaseStatus;
import com.example.payment.exception.GenericServiceException;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentLog;
import com.example.payment.repository.PaymentLogRepository;
import com.example.payment.repository.PaymentRepository;
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
    private final PaymentRepository paymentRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final QueueService queueService;
    private final PurchaseClient purchaseClient;
    private final UserClient userClient;

    @Autowired
    StripeClient(PaymentRepository paymentRepository, PaymentLogRepository paymentLogRepository, QueueService queueService, PurchaseClient purchaseClient, UserClient userClient) {
        this.paymentRepository = paymentRepository;
        this.paymentLogRepository = paymentLogRepository;
        this.queueService = queueService;
        this.purchaseClient = purchaseClient;
        this.userClient = userClient;
        Stripe.apiKey = "sk_test_51KdhdHF64pjijg62TOiq6R0jHqThWZkbhwcASt7Uk9ccVyC0IpW7b7z7VojUy6IXaaJMh0s7aUO8FuRO3RNqBGkq00Yht38PKL";
    }

//    public Customer createCustomer(String token, String email) throws Exception {
//        Map<String, Object> customerParams = new HashMap<String, Object>();
//        customerParams.put("email", email);
//        customerParams.put("source", token);
//        return Customer.create(customerParams);
//    }

    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }

    public void chargeNewCard(String token, double amount, String email, Long advertPackageId,Long userId) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount));
        chargeParams.put("currency", "TRY");
        chargeParams.put("source", token);
        try{
            Charge charge = Charge.create(chargeParams);
            saveSuccessLog(charge,email,advertPackageId,userId);

        }catch (CardException c){
           saveErrorLog(c,email,advertPackageId,userId);
           log.info("Payment Declined " ,c);

        }
    }

//    public void sendEmail(String email, String message,Payment payment){
//        EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
//        emailMessageDTO.setEmail(email);
//        emailMessageDTO.setMessage(message);
//        emailMessageDTO.setCurrency(payment.getCurrency());
//        emailMessageDTO.setDuration(payment.getAdvertPackage().getDuration());
//        emailMessageDTO.setAdvertNumber(payment.getAdvertPackage().getAdvertNumber());
//        emailMessageDTO.setPrice(payment.getAdvertPackage().getPrice());
//        queueService.sendMessage(emailMessageDTO);
//    }

    public void saveSuccessLog(Charge charge, String email,Long advertPackageId,Long userId){
        Payment payment = new Payment();
        payment.setAdvertPackageId(advertPackageId);
        payment.setUserId(userId);
        payment.setCurrency(Currency.TL);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment = paymentRepository.save(payment);
//        sendEmail(email,"Your Payment Has Been Done",payment);
        savePurchase(advertPackageId, userId);
        saveSuccsessPaymentLog(payment,charge);

    }

    public void saveSuccsessPaymentLog(Payment payment, Charge charge){
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setPayment(payment);
        paymentLog.setPaymentDate(ZonedDateTime.now());
        paymentLog.setStatus(charge.getStatus());
        paymentLogRepository.save(paymentLog);
    }

    public void savePurchase(Long advertPackageId, Long userId){
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        AdvertPackageDTO advertPackageDTO = new AdvertPackageDTO();
        advertPackageDTO.setId(advertPackageId);
        purchaseDTO.setUserId(userId);
        purchaseDTO.setPurchaseDate(ZonedDateTime.now());
        purchaseDTO.setAdvertPackage(advertPackageDTO);
        purchaseDTO.setPurchaseStatus(PurchaseStatus.COMPLETED);
        purchaseClient.create(purchaseDTO);
    }

    public void saveErrorLog(CardException c, String email, Long advertPackageId, Long userId){
        Payment payment = new Payment();
        payment.setAdvertPackageId(advertPackageId);
        payment.setUserId(userId);
        payment.setCurrency(Currency.TL);
        payment.setPaymentStatus(PaymentStatus.REJECTED);
        payment = paymentRepository.save(payment);
//        sendEmail(email,"Your Payment Could Not Be Made",payment);
        savePurchase(advertPackageId, userId);
        saveFailPaymentLog(payment,c);

    }
    public void saveFailPaymentLog(Payment payment, CardException c){
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setPayment(payment);
        paymentLog.setPaymentDate(ZonedDateTime.now());
        paymentLog.setStatus(c.getCode());
        paymentLog.setErrorMessage(c.getMessage());
        paymentLogRepository.save(paymentLog);
    }


//    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
//        String sourceCard = getCustomer(customerId).getDefaultSource();
//        Map<String, Object> chargeParams = new HashMap<String, Object>();
//        chargeParams.put("amount", amount);
//        chargeParams.put("currency", "USD");
//        chargeParams.put("customer", customerId);
//        chargeParams.put("source", sourceCard);
//        Charge charge = Charge.create(chargeParams);
//        return charge;
//    }

}
