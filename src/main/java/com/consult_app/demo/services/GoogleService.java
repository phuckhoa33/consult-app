package com.consult_app.demo.services;

public interface GoogleService {
    String oauth2Callback(String code);

    String authorize();

}
