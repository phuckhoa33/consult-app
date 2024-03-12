package com.consult_app.demo.services;

import com.google.api.client.util.DateTime;
import com.google.api.services.people.v1.model.Date;

public interface GoogleService {
    void oauth2Callback(String code);

    String createMeeting(DateTime starDate, DateTime endDate);

    String authorize();
}
