package com.consult_app.demo.services;

import com.consult_app.demo.dtos.InputSendEmailData;

public interface CommonService {
    long createRandomId(int desiredLength);

    String createCurrentDate();

    String sendEmail(InputSendEmailData data);
}
