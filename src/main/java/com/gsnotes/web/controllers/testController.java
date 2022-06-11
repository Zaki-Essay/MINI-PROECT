package com.gsnotes.web.controllers;


import com.gsnotes.bo.Enseignant;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IEnseignantService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.web.models.ExportModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class testController {

    @Autowired
    private IEnseignantService enseignantService;


    @Autowired
    private IModuleService moduleService;



    @RequestMapping("/test")
    public String test(Model model){

        List<Enseignant> enseignants = enseignantService.getAllEnseignant();
        List<Module> modules = moduleService.getAllModules();

        model.addAttribute("enseignants",enseignants);
        model.addAttribute("modules",modules);


        return "admin/test";

    }


}
