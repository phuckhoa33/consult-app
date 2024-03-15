package com.consult_app.demo.services;

import com.google.api.client.util.DateTime;

public interface GoogleService {
    void oauth2Callback(String code);

    String createMeeting(DateTime starDate, DateTime endDate);

    String authorize();
}
