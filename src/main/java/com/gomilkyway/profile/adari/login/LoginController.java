package com.gomilkyway.profile.adari.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @ModelAttribute
    LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }
}
