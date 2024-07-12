package com.joseflores.dojosyninjas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.joseflores.dojosyninjas.models.Dojos;
import com.joseflores.dojosyninjas.services.DojosServices;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController{
    @Autowired
    private DojosServices dojosServices;

    @GetMapping("/dojos/new")
    public String Dojosform(Model model){
        model.addAttribute("dojos", new Dojos());
        return "formdojo.jsp";
    }
    @PostMapping("/dojos/new")
    public String CreateDojos(@Valid @ModelAttribute("dojos") Dojos dojos,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formdojo.jsp";
    }
        dojosServices.createDojo(dojos);
        return "redirect:/dojos/" + dojos.getId();
    }
    @GetMapping("/dojos/{id}")
    public String home(@PathVariable("id") Long id, Model model) {
        Dojos dojos = dojosServices.findById(id);
        if (dojos != null) {
            model.addAttribute("dojos", dojos);
            return "showDojos.jsp";
        } else {
        return "redirect:/dojos/new";
        }
    }

}
