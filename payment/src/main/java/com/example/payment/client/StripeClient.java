package com.example.payment.client;


import com.example.payment.dto.AdvertPackageDTO;
import com.example.payment.dto.CardDTO;
import com.example.payment.dto.PurchaseDTO;
import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import com.example.payment.enums.PurchaseStatus;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentLog;
import com.example.payment.repository.PaymentLogRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.service.QueueService;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class StripeClient {
    private final PaymentRepository paymentRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final QueueService queueService;
    private final PurchaseClient purchaseClient;

    @Autowired
    StripeClient(PaymentRepository paymentRepository, PaymentLogRepository paymentLogRepository, QueueService queueService, PurchaseClient purchaseClient) {
        this.paymentRepository = paymentRepository;
        this.paymentLogRepository = paymentLogRepository;
        this.queueService = queueService;
        this.purchaseClient = purchaseClient;
        Stripe.apiKey = "sk_test_51KdhdHF64pjijg62TOiq6R0jHqThWZkbhwcASt7Uk9ccVyC0IpW7b7z7VojUy6IXaaJMh0s7aUO8FuRO3RNqBGkq00Yht38PKL";
    }

    public void getToken(@RequestBody CardDTO cardDTO, @RequestHeader(value = "id") UUID id) throws Exception {
        Map<String, Object> card = new HashMap<>();
        card.put("number", cardDTO.getNumber());
        card.put("exp_month", cardDTO.getExp_month());
        card.put("exp_year", cardDTO.getExp_year());
        card.put("cvc", cardDTO.getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);
        Token token = Token.create(params);
        cardDTO.setToken(token.getId());
        cardDTO.setUserId(id);
        chargeNewCard(cardDTO);
    }

    public void chargeNewCard(CardDTO cardDTO) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int) cardDTO.getAmount());
        chargeParams.put("currency", "TRY");
        chargeParams.put("source", cardDTO.getToken());
        try{
            Charge charge = Charge.create(chargeParams);
            saveSuccessLog(charge,cardDTO);
            log.error("Payment Success : " ,charge);
        }catch (CardException c){
            saveErrorLog(c,cardDTO);
            log.error("Payment Declined : " ,c);
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

    public void saveSuccessLog(Charge charge, CardDTO cardDTO){
        Payment payment = new Payment();
        payment.setAdvertPackageId(cardDTO.getAdvertPackageId());
        payment.setUserId(cardDTO.getUserId());
        payment.setCurrency(Currency.TL);
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment = paymentRepository.save(payment);
//        sendEmail(email,"Your Payment Has Been Done",payment);
        savePurchase(cardDTO.getAdvertPackageId(), cardDTO.getUserId());
        saveSuccsessPaymentLog(payment,charge);
    }

    public void saveSuccsessPaymentLog(Payment payment, Charge charge){
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setPayment(payment);
        paymentLog.setPaymentDate(ZonedDateTime.now());
        paymentLog.setStatus(charge.getStatus());
        paymentLogRepository.save(paymentLog);
    }

    public void savePurchase(Long advertPackageId, UUID userId){
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        AdvertPackageDTO advertPackageDTO = new AdvertPackageDTO();
        advertPackageDTO.setId(advertPackageId);
        purchaseDTO.setUserId(userId);
        purchaseDTO.setPurchaseDate(ZonedDateTime.now());
        purchaseDTO.setAdvertPackage(advertPackageDTO);
        purchaseDTO.setPurchaseStatus(PurchaseStatus.COMPLETED);
        purchaseClient.create(purchaseDTO,userId);
    }

    public void saveErrorLog(CardException c, CardDTO cardDTO){
        Payment payment = new Payment();
        payment.setAdvertPackageId(cardDTO.getAdvertPackageId());
        payment.setUserId(cardDTO.getUserId());
        payment.setCurrency(Currency.TL);
        payment.setPaymentStatus(PaymentStatus.REJECTED);
        payment = paymentRepository.save(payment);
//        sendEmail(email,"Your Payment Could Not Be Made",payment);
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
}
