package com.oop.bilimbaqyla.controllers;


import com.oop.bilimbaqyla.models.User;
import com.oop.bilimbaqyla.repositories.ParentRepo;
import com.oop.bilimbaqyla.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static boolean isUsernameExist;
    private static boolean isPasswdSuits;

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
        if (isFormValid() == false) {
            model.addAttribute("isUsernameValid", !isValidUsername);
            model.addAttribute("isPasswdValid", !isValidPaswwd);

            return "login";
        }

        TeacherRepo teacherRepo = new TeacherRepo();
        ParentRepo parentRepo = new ParentRepo();
        boolean isTeacher = teacherRepo.checkUsername(user.getUsername());
        boolean isParent = parentRepo.checkUsername(user.getUsername());

        if (isTeacher) {
            String teacherPasswd = teacherRepo.getPasswdByUsername(user.getUsername());
            if (teacherPasswd != null && teacherPasswd.equals(user.getPasswd())) {
                redirectAttributes.addFlashAttribute("teacher", teacherRepo.getTeacherByUsername(user.getUsername()));
                return "redirect:/teacherapp";
            } else {
                // If password is wrong, stay on login page
                model.addAttribute("loginError", "Invalid username or password.");
                return "login";
            }
        } else if (isParent) {
            String parentPasswd = parentRepo.getPasswdByUsername(user.getUsername());
            if (parentPasswd != null && parentPasswd.equals(user.getPasswd())) {
                redirectAttributes.addFlashAttribute("parent", parentRepo.getParentByUsername(user.getUsername()));
                return "redirect:/parentapp";
            } else {
                // If password is wrong, stay on login page
                model.addAttribute("loginError", "Invalid username or password.");
                return "login";
            }
        }

        // If neither teacher nor parent, stay on login page
        model.addAttribute("loginError", "Invalid username or password.");
        return "login";

        //redirectAttributes.addFlashAttribute("user", user);
        //return "redirect:/";


    }
    private static void validateForm(String username, String passwd){
        isValidUsername = validateUsername(username);
        isValidPaswwd = validatePasswd(passwd);
    }

    private static boolean isFormValid () {
        if (isValidUsername && isValidPaswwd) {
            return true;
        }
        return false;
    }

    private static boolean validateUsername(String username){
        if (username.length() <= 1) {
            return false;
        }
        return true;
    }

    private static boolean validatePasswd (String passwd){

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passwd);
        if (matcher.find() == true) {
            return true;
        }
        return false;
    }
}
