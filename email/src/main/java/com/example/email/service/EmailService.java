package com.example.email.service;

import com.example.email.client.AdvertPackageClient;
import com.example.email.client.UserClient;
import com.example.email.dto.AdvertPackageDTO;
import com.example.email.dto.EmailDTO;
import com.example.email.dto.UserDTO;
import com.example.email.model.Email;
import com.example.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService{

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final AdvertPackageClient advertPackageClient;
    private final UserClient userClient;
    private final EmailRepository emailRepository;

    public void sendMail(EmailDTO emailDTO)
            throws MessagingException {

        final Context ctx = getClient(emailDTO);
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart

        message.setTo(emailDTO.getEmail());
        message.setText("bill-email", true);

        final String htmlContent = springTemplateEngine.process("bill-email", ctx);
        message.setText(htmlContent, true); // true = isHtml
        mailSender.send(mimeMessage);
        Email email = new Email();
        email.setReceiverEmail(emailDTO.getEmail());
        email.setSenderEmail("emlakk.hepsii@gmail.com");
        emailRepository.save(email);
    }

    public Context getClient(EmailDTO emailDTO){
        final Context ctx = new Context();
        AdvertPackageDTO advertPackage = advertPackageClient.getById(emailDTO.getAdvertPackageId());
        UserDTO user = userClient.getUserById(emailDTO.getUserId());
        ctx.setVariable("name", user.getFirstName());
        ctx.setVariable("surname", user.getLastName());
        ctx.setVariable("email", user.getEmail());
        ctx.setVariable("price", advertPackage.getPrice());
        ctx.setVariable("currency", advertPackage.getCurrency());
        ctx.setVariable("duration", advertPackage.getDuration());
        ctx.setVariable("packageName", advertPackage.getName());
        return ctx;
    }
}
