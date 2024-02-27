package com.oop.bilimbaqyla.controllers;


import com.oop.bilimbaqyla.models.Parent;
import com.oop.bilimbaqyla.repositories.ParentRepo;
import com.oop.bilimbaqyla.utility.FieldsChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private static boolean isNameValid;
    private static boolean isLastnameValid;
    private static boolean isPhoneValid;
    private static boolean isEmailValid;
    private static boolean isPasswdValid;
    private static boolean isUserExist;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("parent", new Parent());


        return "register";
    }

    @PostMapping("/register")
    public String registerUser( @ModelAttribute Parent parent, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        validate(parent.getFirstname(),parent.getLastname(),parent.getPhoneNumber(),parent.getPasswd(),parent.getEmail());
        checkExistingParent(parent.getUsername());
        if(isValidForm() == false || isUserExist){
            model.addAttribute("isNameNotValid", !isNameValid);
            model.addAttribute("isLastnameNotValid", !isLastnameValid);
            model.addAttribute("isPhoneNotValid", !isPhoneValid);
            model.addAttribute("isEmailNotValid", !isEmailValid);
            model.addAttribute("isPasswdNotValid", !isPasswdValid);
            model.addAttribute("isUserExist", isUserExist);
            return "register";
        }

        ParentRepo parentRepo = new ParentRepo();
        parentRepo.create(parent);
        redirectAttributes.addFlashAttribute("parent", parent);
        return "redirect:/parentapp";


    }

    private static void validate(String name, String lastname, String phone, String passwd, String email){
        FieldsChecker fieldsChecker = new FieldsChecker();

        isEmailValid = fieldsChecker.isEmailValid(email);
        isLastnameValid = fieldsChecker.isLastnameValid(lastname);
        isNameValid = fieldsChecker.isNameValid(name);
        isPasswdValid = fieldsChecker.isPasswdValid(passwd);
        isPhoneValid = fieldsChecker.isPhoneValid(phone);


    }
    private boolean isValidForm(){
        if(isEmailValid && isLastnameValid && isNameValid && isPasswdValid && isPhoneValid){
            return true;
        }
        return false;

    }

    private void checkExistingParent(String username){
        ParentRepo parentRepo = new ParentRepo();

        isUserExist = parentRepo.checkUsername(username);
    }


}
