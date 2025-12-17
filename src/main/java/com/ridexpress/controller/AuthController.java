package com.ridexpress.controller;

import com.ridexpress.entity.User;
import com.ridexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        user.setRole("user");
        userService.registerUser(user);
        model.addAttribute("message", "Registration Successful! Please Login.");
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "carId", required = false) String carId, Model model) {
        model.addAttribute("carId", carId); 
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        @RequestParam(required = false) String carId,
                        Model model) {

        User user = userService.loginUser(email, password);

        if (user != null) {
            
            if (carId != null && !carId.isEmpty()) {
                return "redirect:/booking?carId=" + carId;
            }
            return "redirect:/booking"; 
        }

        model.addAttribute("error", "Invalid Credentials!");
        return "login";
    }
}
