package com.mvcproject.v1.controller;

import com.mvcproject.v1.model.CeldaModel;
import com.mvcproject.v1.model.RegistroModel;
import com.mvcproject.v1.model.TarifaModel;
import com.mvcproject.v1.model.TipoVehiculoModel;
import com.mvcproject.v1.model.VehiculoModel;
import com.mvcproject.v1.repository.CeldaRepository;
import com.mvcproject.v1.repository.RegistroRepository;
import com.mvcproject.v1.repository.TarifaRepository;
import com.mvcproject.v1.repository.TipoVehiculoRepository;
import com.mvcproject.v1.repository.VehiculoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

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

    // Endpoint para mostrar el formulario de registro de vehículo
    @Operation(summary = "Mostrar formulario de registro de vehículo", description = "Muestra el formulario para registrar un nuevo vehículo.")
    @GetMapping("/nuevo")
    public String mostrarFormularioVehiculo(Model model) {
        model.addAttribute("vehiculo", new VehiculoModel());
        model.addAttribute("tipoVehiculos", tipoVehiculoRepository.findAll());
        model.addAttribute("celdas", new ArrayList<>());

        return "ingreso"; // Vista que muestra el formulario de vehículo
    }

    // Endpoint para guardar un nuevo vehículo con tipo fijo (ID=1)
    @Operation(summary = "Registrar un nuevo vehículo", description = "Guarda un nuevo vehículo en la base de datos.", responses = {
            @ApiResponse(responseCode = "200", description = "Vehículo registrado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/celdas")
    @ResponseBody
    public List<CeldaModel> obtenerCeldasPorTipo(@RequestParam("tipoVehiculoId") Long tipoVehiculoId) {
        // Buscar solo celdas libres que aceptan ese tipo de vehículo
        return celdaRepository.findByTipoVehiculoIdAndLibreTrue(tipoVehiculoId);
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(
            @RequestParam("placa") String placa,
            @RequestParam("tipoVehiculoId") Long tipoVehiculoId,
            @RequestParam("celdaId") Long celdaId) {

        try {
            // Buscar o crear el vehículo
            VehiculoModel vehiculo = vehiculoRepository.findByPlaca(placa).orElseGet(() -> {
                VehiculoModel nuevoVehiculo = new VehiculoModel();
                nuevoVehiculo.setPlaca(placa);
                TipoVehiculoModel tipo = tipoVehiculoRepository.findById(tipoVehiculoId)
                        .orElseThrow(() -> new IllegalArgumentException("Tipo de vehículo no encontrado"));
                nuevoVehiculo.setTipoid(tipo);
                return vehiculoRepository.save(nuevoVehiculo);
            });

            // Obtener la celda seleccionada
            CeldaModel celda = celdaRepository.findById(celdaId)
                    .orElseThrow(() -> new IllegalArgumentException("Celda no encontrada"));

            if (!celda.isLibre()) {
                // Si la celda está ocupada, no permitir registro
                return "redirect:/registro/nuevo?error=celda_ocupada";
            }

            // Marcar la celda como ocupada
            celda.setLibre(false);
            celdaRepository.save(celda);

            // Crear el registro sin asociar la tarifa aún
            RegistroModel registro = new RegistroModel();
            registro.setVehiculo(vehiculo);
            registro.setCelda(celda);
            registro.setFechaEntrada(LocalDateTime.now());
            registro.setFechaSalida(null); // aún no ha salido
            registroRepository.save(registro);

            return "redirect:/registro/nuevo?success";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/registro/nuevo?error=exception";
        }
    }

    @GetMapping("/salida")
    public String mostrarFormularioSalida(Model model) {
        return "salida"; // Vista que muestra el formulario de vehículo
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

        // Obtener la tarifa asociada al tipo de vehículo
        TarifaModel tarifa = tarifaRepository.findByTipoVehiculo(vehiculo.getTipoid());

        if (tarifa == null) {
            throw new RuntimeException("No se encontró una tarifa para el tipo de vehículo");
        }

        // Calcular el total a pagar
        BigDecimal total = tarifa.getValorHora().multiply(BigDecimal.valueOf(horas));

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("registro", registro);
        model.addAttribute("horas", horas);
        model.addAttribute("total", total);
        model.addAttribute("placa", placa);

        return "salida"; // Devolver a la misma vista con los datos
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
        registroRepository.save(registro);

        // Liberar la celda
        CeldaModel celda = registro.getCelda();
        celda.setLibre(true);
        celdaRepository.save(celda);

        model.addAttribute("mensaje", "Salida registrada exitosamente.");
        return "salida"; // Muestra el mensaje de éxito
    }
}
