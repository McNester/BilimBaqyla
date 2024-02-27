package com.oop.bilimbaqyla.controllers;


import com.oop.bilimbaqyla.models.Parent;
import com.oop.bilimbaqyla.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    private static boolean isValidUsername;
    private static boolean isValidPaswwd;
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        validateForm(user.getUsername(), user.getPasswd());
        if(isFormValid() == false){
            model.addAttribute("isUsernameValid", !isValidUsername);
            model.addAttribute("isPasswdValid", !isValidPaswwd);

            return "login";
        }

        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/";
    }

    private static void validateForm(String username, String passwd){
        isValidUsername = validateUsername(username);
        isValidPaswwd = validatePasswd(passwd);
    }

    private static boolean isFormValid(){
        if(isValidUsername && isValidPaswwd){
            return true;
        }
        return false;
    }

    private static boolean validateUsername(String username){
        if(username.length() <= 1){
            return false;
        }
        return true;
    }

    private static boolean validatePasswd(String passwd){

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passwd);
        if(matcher.find() == true){
            return true;
        }
        return false;
    }
}
