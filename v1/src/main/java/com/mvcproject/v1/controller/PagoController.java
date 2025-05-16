package com.mvcproject.v1.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;


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
public String listarPagos(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          Model model) {

    // Pagos del día
    List<PagoModel> pagosHoy = pagoService.findPagosDeHoy();

    // Paginación de todos los pagos
    Pageable pageable = PageRequest.of(page, size);
    Page<PagoModel> paginaPagos = pagoService.getPagosPaginados(pageable);

    model.addAttribute("pagos", pagosHoy);
    model.addAttribute("paginaPagos", paginaPagos);
    model.addAttribute("paginaActual", page);
    model.addAttribute("totalPaginas", paginaPagos.getTotalPages());

    return "pagos";
}



}
