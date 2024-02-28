package com.oop.bilimbaqyla.controllers;

import com.oop.bilimbaqyla.models.Child;
import com.oop.bilimbaqyla.models.Parent;
import com.oop.bilimbaqyla.repositories.ChildRepo;
import com.oop.bilimbaqyla.repositories.ParentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ParentAppController {

    Parent parent;

    @GetMapping("/parentapp")
    public String home(HttpServletRequest request, Model model){


        if (model.containsAttribute("parent")) {
            parent = (Parent) model.asMap().get("parent");


            // Now you can use 'message' as needed

            System.out.println(parent.getUsername());

            ArrayList<Child> children = new ArrayList<>();
            ChildRepo childRepo = new ChildRepo();

            children = childRepo.getChildrenByParentId(parent.getParentId());



            if (parent != null) {
                model.addAttribute("children",children);
                model.addAttribute("parent",parent);
            }
            if (!model.containsAttribute("newChild")) {
                model.addAttribute("newChild", new Child());
            }
        }


        return "parentApp";
    }

    @PostMapping("/submitChild")
    public String submitChild(@ModelAttribute("newChild") Child child, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        child.setParentId(parent.getParentId());

        ParentRepo parentRepo = new ParentRepo();
        ChildRepo childRepo = new ChildRepo();

        child.setChildId(childRepo.create(child));

        parentRepo.addChildToParent(child.getParentId(), child.getChildId());

        ArrayList<Child> children = new ArrayList<>();

        children = childRepo.getChildrenByParentId(parent.getParentId());



        redirectAttributes.addFlashAttribute("parent",parent);

        return "redirect:/parentapp";
    }


}
