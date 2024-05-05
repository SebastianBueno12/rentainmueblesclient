package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.servicio.BusquedaInmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/inmuebles")
public class BusquedaInmuebleControlador {

    @Autowired
    private BusquedaInmuebleServicio servicio;

    @GetMapping("/buscar")
    public String buscarInmuebles(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
            @RequestParam(required = false) Integer numPersonas,
            Model model) {
        List<BusquedaInmueble> busquedaInmuebles = servicio.buscarInmuebles(ciudad, fechaLlegada, fechaSalida, numPersonas);
        model.addAttribute("inmuebles", busquedaInmuebles);
        return "buscar-inmuebles";
    }
}
