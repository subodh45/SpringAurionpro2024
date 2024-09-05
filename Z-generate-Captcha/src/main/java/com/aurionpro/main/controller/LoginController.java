package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aurionpro.main.util.CaptchaUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private CaptchaUtil captchaUtil;

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        String captchaImage = captchaUtil.generateCaptcha(session);
        model.addAttribute("captchaImage", captchaImage);
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, String captchaResponse, HttpSession session, Model model) {
        if (!captchaUtil.validateCaptcha(captchaResponse, session)) {
            model.addAttribute("error", "Invalid CAPTCHA");
            return "login";
        }

        // Authenticate user here (logic not shown)
        return "redirect:/home";
    }
}
