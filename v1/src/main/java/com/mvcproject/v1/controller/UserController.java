package com.mvcproject.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


import com.mvcproject.v1.service.UserService;


@Controller
@RequestMapping(value = "/user")
public class UserController {

     @Autowired 
     private UserService userService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("users", userService.listar());
        return "users/listarusuarios";
    }
    
    
    
}
