package com.example.porfoliotest.Controller;

import com.example.porfoliotest.model.User;
import com.example.porfoliotest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class RegisterController {

    @Autowired
    private UserService userServices;

    @GetMapping("/register")
    public String registUser(User user) {

        return "/register";
    }

    @PostMapping("/register")
    public String register(@Validated User user, BindingResult bindingResult, Model model) {

        model.addAttribute("user", new User());

        if(bindingResult.hasErrors()) {
            return ("/register");
        }


        userServices.createUser(user);
        return "redirect:/login";
    }
}