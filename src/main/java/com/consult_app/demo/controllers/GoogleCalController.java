package com.consult_app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.consult_app.demo.services.GoogleService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GoogleCalController {

    @Autowired
    GoogleService googleService;

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
        return new RedirectView(googleService.authorize());
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public String oauth2Callback(@RequestParam(value = "code") String code) {
        googleService.oauth2Callback(code);

        String meetingDetail = googleService.createMeeting(null, null);
        System.out.println(meetingDetail);

        return "meeting/set_meeting";
    }

}