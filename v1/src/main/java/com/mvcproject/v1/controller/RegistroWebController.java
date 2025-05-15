package com.mvcproject.v1.controller;

import com.mvcproject.v1.model.CeldaModel;
import com.mvcproject.v1.model.PagoModel;
import com.mvcproject.v1.model.RegistroModel;
import com.mvcproject.v1.model.TarifaModel;
import com.mvcproject.v1.model.TipoVehiculoModel;
import com.mvcproject.v1.model.VehiculoModel;
import com.mvcproject.v1.repository.CeldaRepository;
import com.mvcproject.v1.repository.PagoRepository;
import com.mvcproject.v1.repository.RegistroRepository;
import com.mvcproject.v1.repository.TarifaRepository;
import com.mvcproject.v1.repository.TipoVehiculoRepository;
import com.mvcproject.v1.repository.VehiculoRepository;


import java.util.ArrayList;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registro")
public class RegistroWebController {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private CeldaRepository celdaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private TarifaRepository tarifaRepository;

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @Autowired
    private PagoRepository pagosRepository;

    @GetMapping("/nuevo")
    public String mostrarFormularioVehiculo(Model model) {
        model.addAttribute("vehiculo", new VehiculoModel());
        model.addAttribute("tipoVehiculos", tipoVehiculoRepository.findAll());
        model.addAttribute("celdas", new ArrayList<>());
        return "ingreso";
    }

    @GetMapping("/celdas")
    @ResponseBody
    public List<CeldaModel> obtenerCeldasPorTipo(@RequestParam("tipoVehiculoId") Long tipoVehiculoId) {
        return celdaRepository.findByTipoVehiculoIdAndLibreTrue(tipoVehiculoId);
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(
            @RequestParam("placa") String placa,
            @RequestParam("tipoVehiculoId") Long tipoVehiculoId,
            @RequestParam("celdaId") Long celdaId,
            RedirectAttributes redirectAttributes) {

        try {
            VehiculoModel vehiculo = vehiculoRepository.findByPlaca(placa).orElseGet(() -> {
                VehiculoModel nuevoVehiculo = new VehiculoModel();
                nuevoVehiculo.setPlaca(placa);
                TipoVehiculoModel tipo = tipoVehiculoRepository.findById(tipoVehiculoId)
                        .orElseThrow(() -> new IllegalArgumentException("Tipo de vehículo no encontrado"));
                nuevoVehiculo.setTipoid(tipo);
                return vehiculoRepository.save(nuevoVehiculo);
            });

            CeldaModel celda = celdaRepository.findById(celdaId)
                    .orElseThrow(() -> new IllegalArgumentException("Celda no encontrada"));

            if (!celda.isLibre()) {
                redirectAttributes.addFlashAttribute("error", "La celda seleccionada está ocupada.");
                return "redirect:/registro/nuevo";
            }

            celda.setLibre(false);
            celdaRepository.save(celda);

            RegistroModel registro = new RegistroModel();
            registro.setVehiculo(vehiculo);
            registro.setCelda(celda);
            registro.setFechaEntrada(LocalDateTime.now());
            registro.setFechaSalida(null);
            registroRepository.save(registro);

            redirectAttributes.addFlashAttribute("mensaje", "Vehículo registrado exitosamente.");
            return "redirect:/registro/nuevo";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al registrar el vehículo: " + e.getMessage());
            return "redirect:/registro/nuevo";
        }
    }

    @GetMapping("/salida")
    public String mostrarFormularioSalida(Model model) {
        return "salida";
    }

    @PostMapping("/salida")
    public String consultarSalida(@RequestParam("placa") String placa, Model model) {
        Optional<VehiculoModel> vehiculoOpt = vehiculoRepository.findByPlaca(placa);

        if (vehiculoOpt.isEmpty()) {
            model.addAttribute("error", "Vehículo no encontrado.");
            return "salida";
        }

        VehiculoModel vehiculo = vehiculoOpt.get();
        Optional<RegistroModel> registroOpt = registroRepository.findByVehiculoAndFechaSalidaIsNull(vehiculo);

        if (registroOpt.isEmpty()) {
            model.addAttribute("error", "No hay un registro de entrada activo para esta placa.");
            return "salida";
        }

        RegistroModel registro = registroOpt.get();
        LocalDateTime fechaEntrada = registro.getFechaEntrada();
        LocalDateTime fechaSalida = LocalDateTime.now();

        Duration duracion = Duration.between(fechaEntrada, fechaSalida);
        long horas = Math.max(duracion.toHours(), 1);

        TarifaModel tarifa = tarifaRepository.findByTipoVehiculo(vehiculo.getTipoid());

        if (tarifa == null) {
            throw new RuntimeException("No se encontró una tarifa para el tipo de vehículo");
        }

        BigDecimal total = tarifa.getValorHora().multiply(BigDecimal.valueOf(horas));

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("registro", registro);
        model.addAttribute("horas", horas);
        model.addAttribute("total", total);
        model.addAttribute("placa", placa);

        return "salida";
    }

    @PostMapping("/salida/confirmar")
    public String confirmarSalida(@RequestParam("placa") String placa, Model model) {
        Optional<VehiculoModel> vehiculoOpt = vehiculoRepository.findByPlaca(placa);

        if (vehiculoOpt.isEmpty()) {
            model.addAttribute("error", "Vehículo no encontrado.");
            return "salida";
        }

        VehiculoModel vehiculo = vehiculoOpt.get();
        Optional<RegistroModel> registroOpt = registroRepository.findByVehiculoAndFechaSalidaIsNull(vehiculo);

        if (registroOpt.isEmpty()) {
            model.addAttribute("error", "No hay un registro de entrada activo para esta placa.");
            return "salida";
        }

        RegistroModel registro = registroOpt.get();
        registro.setFechaSalida(LocalDateTime.now());
        System.out.println("Registro que se va a guardar: " + registro);
        registroRepository.save(registro);

        // Liberar la celda
        CeldaModel celda = registro.getCelda();
        celda.setLibre(true);
        celdaRepository.save(celda);

        // Calcular horas y monto
        Duration duracion = Duration.between(registro.getFechaEntrada(), registro.getFechaSalida());
        long horas = Math.max(duracion.toHours(), 1);

        TarifaModel tarifa = tarifaRepository.findByTipoVehiculo(vehiculo.getTipoid());
        if (tarifa == null) {
            throw new RuntimeException("No se encontró una tarifa para el tipo de vehículo");
        }

        BigDecimal total = tarifa.getValorHora().multiply(BigDecimal.valueOf(horas));

        // Registrar el pago
        PagoModel pago = new PagoModel();
        pago.setRegistro(registro);
        pago.setMonto(total);
        pago.setFechaPago(LocalDateTime.now());
        System.out.println("pago que se va a guardar: " + pago);
        pagosRepository.save(pago);

        model.addAttribute("mensaje", "Salida registrada y pago realizado exitosamente.");
        return "salida";
    }
}
