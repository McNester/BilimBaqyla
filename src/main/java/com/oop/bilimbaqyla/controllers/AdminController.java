package com.oop.bilimbaqyla.controllers;

import com.oop.bilimbaqyla.models.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @GetMapping("/adminPage")
    public String home(HttpServletRequest request, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        if (admin != null) {
            model.addAttribute("admin", admin);
        }

        return "adminPage";
    }
}
