package com.consult_app.demo.services;

import jakarta.servlet.http.HttpServletRequest;

public interface GoogleService {
    String googleConnectionStatus(HttpServletRequest request) throws Exception;

}
