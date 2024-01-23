package com.consult_app.demo.services.Impl;

import com.consult_app.demo.dtos.EmailDetail;
import com.consult_app.demo.dtos.InputEmailData;
import com.consult_app.demo.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(InputEmailData detail, String template) {
        EmailDetail details = detail.getDetail();

        try {
            Context context = new Context();

            context.setVariable("path", details.getVariables().getPath());

            String process = templateEngine.process(template, context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("Welcome " + details.getSubject());
            helper.setText(process, true);
            helper.setTo(details.getRecipient());
            javaMailSender.send(mimeMessage);
            return "Sent";

        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(InputEmailData details, String template) {
        return null;
    }
}
