package com.aurionpro.main.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.github.cage.Cage;
import com.github.cage.GCage;

import jakarta.servlet.http.HttpSession;

@Component
public class CaptchaUtil {

    private final Cage cage = new GCage();

    public String generateCaptcha(HttpSession session) {
        String captchaText = cage.getTokenGenerator().next();
        session.setAttribute("captcha", captchaText);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            cage.draw(captchaText, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public boolean validateCaptcha(String userCaptchaResponse, HttpSession session) {
        String sessionCaptcha = (String) session.getAttribute("captcha");
        return sessionCaptcha != null && sessionCaptcha.equalsIgnoreCase(userCaptchaResponse);
    }
}
