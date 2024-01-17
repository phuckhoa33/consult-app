package com.consult_app.demo.services;

import com.consult_app.demo.dto.InputEmailData;

public interface EmailService {// Method
    // To send a simple email
    String sendSimpleMail(InputEmailData details, String template);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(InputEmailData details, String template);
}
