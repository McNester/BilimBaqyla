package com.oop.bilimbaqyla.controllers;

import com.oop.bilimbaqyla.models.Admin;
import com.oop.bilimbaqyla.models.Parent;
import com.oop.bilimbaqyla.repositories.AdminRepo;
import com.oop.bilimbaqyla.repositories.ParentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class AdminController {

    @GetMapping("/adminPage")
    public String home(HttpServletRequest request, Model model) {
//        Admin admin = (Admin) request.getSession().getAttribute("admin");
//
//        if (admin != null) {
//            model.addAttribute("admin", admin);
//        }
        AdminRepo adminRepo = new AdminRepo();
        ParentRepo parentRepo = new ParentRepo();
        Admin admin = adminRepo.getAdminById(1);
        ArrayList<Integer> parentsId = adminRepo.getIdOfParentsWaiting(admin.getAdminId());
        ArrayList<Parent> parents = new ArrayList<>();

        for (int id : parentsId) {
            parents.add(parentRepo.getParentById(id));
        }
        model.addAttribute("parents",parents);
        return "adminPage";
    }
}
