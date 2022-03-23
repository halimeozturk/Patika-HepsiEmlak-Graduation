package com.example.email.service;

import com.example.email.client.AdvertPackageClient;
import com.example.email.client.UserClient;
import com.example.email.repository.EmailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

//@SpringBootTest
//public class EmailServiceTest {
//    @InjectMocks
//    private EmailService emailService;
//    @Mock
//    private JavaMailSender mailSender;
//    @Mock
//    private SpringTemplateEngine springTemplateEngine;
//    @Mock
//    private AdvertPackageClient advertPackageClient;
//    @Mock
//    private UserClient userClient;
//    @Mock
//    private EmailRepository emailRepository;
//
//
//
//}
