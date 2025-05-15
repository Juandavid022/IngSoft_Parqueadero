package com.mvcproject.v1.controller;

import com.mvcproject.v1.service.PagoService;
import com.mvcproject.v1.model.PagoModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public String listarPagos(Model model) {
        List<PagoModel> pagos = pagoService.findPagosDeHoy();
        model.addAttribute("pagos", pagos);
        return "pagos";
    }

}
