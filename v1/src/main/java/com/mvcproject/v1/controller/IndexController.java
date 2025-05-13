package com.mvcproject.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/parqueadero")
public class IndexController {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

}
