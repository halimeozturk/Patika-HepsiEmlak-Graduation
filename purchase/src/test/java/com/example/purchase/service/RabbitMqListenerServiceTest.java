//package com.example.purchase.service;
//
//import com.example.purchase.dto.AdvertPackageDTO;
//import com.example.purchase.dto.PurchaseDTO;
//import com.example.purchase.enums.Currency;
//import com.example.purchase.enums.PurchaseStatus;
//import com.example.purchase.model.AdvertPackage;
//import com.example.purchase.model.Purchase;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.UnsupportedEncodingException;
//import java.time.ZonedDateTime;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest
//@EnableRabbit
//public class RabbitMqListenerServiceTest {
//
//    @Mock
//    private PurchaseService purchaseService;
//
//    @InjectMocks
//    RabbitMqListenerService rabbitMqListenerService;
//
//    @Test
//    void receiveMessage() throws UnsupportedEncodingException {
//        Mockito.when(purchaseService.create(any(),any())).thenReturn(preparePurchaseDTO());
//        rabbitMqListenerService.receiveMessage(any());
//    }
//
//
//    private PurchaseDTO preparePurchaseDTO(){
//        PurchaseDTO purchase = new PurchaseDTO();
//        purchase.setPaymentId(1L);
//        purchase.setAdvertPackage(prepareAdvertPackageDTO());
//        purchase.setId(1L);
//        purchase.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
//        purchase.setPurchaseDate(ZonedDateTime.now());
//        return purchase;
//    }
//
//    private Purchase preparePurchase(){
//        Purchase purchase = new Purchase();
//        purchase.setPaymentId(1L);
//        purchase.setAdvertPackage(prepareAdvertPackage());
//        purchase.setId(1L);
//        purchase.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
//        purchase.setPurchaseDate(ZonedDateTime.now());
//        return purchase;
//    }
//
//    private AdvertPackageDTO prepareAdvertPackageDTO(){
//        AdvertPackageDTO advertPackageDTO = new AdvertPackageDTO();
//        advertPackageDTO.setId(1L);
//        advertPackageDTO.setAdvertNumber(1);
//        advertPackageDTO.setDuration(1);
//        advertPackageDTO.setPrice(1);
//        advertPackageDTO.setCurrency(Currency.TL);
//        return advertPackageDTO;
//    }
//
//    private AdvertPackage prepareAdvertPackage(){
//        AdvertPackage advertPackage = new AdvertPackage();
//        advertPackage.setId(1L);
//        advertPackage.setAdvertNumber(1);
//        advertPackage.setDuration(1);
//        advertPackage.setPrice(1);
//        advertPackage.setCurrency(Currency.TL);
//        return advertPackage;
//    }
//
//
//}
