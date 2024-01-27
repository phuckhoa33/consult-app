package com.consult_app.demo.services.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consult_app.demo.dtos.EmailDetail;
import com.consult_app.demo.dtos.EmailVariable;
import com.consult_app.demo.dtos.InputEmailData;
import com.consult_app.demo.dtos.InputSendEmailData;
import com.consult_app.demo.services.CommonService;
import com.consult_app.demo.services.EmailService;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    EmailService emailService;

    @Override
    public long createRandomId(int desiredLength) {
        if (desiredLength <= 0) {
            throw new IllegalArgumentException("Desired length must be a positive integer.");
        }

        Random random = new Random();
        long min = (long) Math.pow(10, desiredLength - 1);
        long max = (long) Math.pow(10, desiredLength) - 1;

        return min + random.nextLong() % (max - min + 1);
    }

    @Override
    public String createCurrentDate() {
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the current date using the SimpleDateFormat
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }

    @Override
    public String sendEmail(InputSendEmailData data) {
        String message = "Send Email is successfully";
        try {

            InputEmailData emailData = new InputEmailData();
            EmailDetail details = new EmailDetail();
            details.setRecipient(data.getEmail());
            details.setSubject(message);
            EmailVariable variables = EmailVariable.builder()
                    .path(data.getUrl())
                    .build();
            details.setVariables(variables);
            emailData.setDetail(details);

            emailService.sendSimpleMail(emailData, data.getTemplate());
        } catch (Exception e) {
            e.printStackTrace();
            message = "Send email is failed";
        }

        return message;
    }

}
