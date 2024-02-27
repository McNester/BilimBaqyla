package com.oop.bilimbaqyla.controllers;

import com.oop.bilimbaqyla.models.Child;
import com.oop.bilimbaqyla.models.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ParentAppController {

    @GetMapping("/parentapp")
    public String home(HttpServletRequest request, Model model){
            Parent parent = (Parent) request.getSession().getAttribute("parent");

            ArrayList<Child> children = new ArrayList<>();



            if (parent != null) {
                model.addAttribute("parent", parent);
                model.addAttribute("children",);
            }

            return "parentApp";
    }

}
