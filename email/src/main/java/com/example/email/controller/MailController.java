//package com.example.email.controller;
//
//import com.example.email.dto.EmailDTO;
//import com.example.email.service.EmailService;
//
//
//
//import javax.mail.MessagingException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//
//@RestController
//@RequestMapping("/email-test")
//@RequiredArgsConstructor
//public class MailController {
//    private final EmailService emailService;
//
//    @PostMapping
//    void create(@RequestBody EmailDTO emailDTO) throws MessagingException {
//        emailService.sendMail(emailDTO);
//    }
//
//    @GetMapping("/test")
//    void test() throws MessagingException {
//        EmailDTO emailDTO = new EmailDTO();
//        emailDTO.setEmail("ozturkk.halimee@gmail.com");
//        emailDTO.setAdvertPackageId(1L);
//        emailDTO.setUserId(UUID.fromString("8311aa94-c0b1-40a5-8b48-a63a62f56c8b"));
//        emailService.sendMail(emailDTO);
//
//        System.out.println("hello ");
//    }
//}
