package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaAvanzadaInmueble;
import co.ucentral.rentainmueblesclientes.servicio.BusquedaAvanzadaInmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.time.LocalDate;

@Controller
@RequestMapping("/api/inmuebles")
public class BusquedaAvanzadaInmuebleControlador {

    @Autowired
    private BusquedaAvanzadaInmuebleServicio servicioAvanzado;

    // Muestra el formulario de búsqueda avanzada
    @GetMapping("/formulario-busqueda-avanzada")
    public String mostrarFormularioBusqueda() {
        return "formulario-busqueda-avanzada";
    }

    // Procesa la búsqueda avanzada
    @GetMapping("/buscar-avanzada")
    public String buscarConFiltros(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String zona,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin,
            @RequestParam(required = false) Boolean tienePiscina,
            @RequestParam(required = false) Boolean tieneWifi,
            Model model) {

        List<BusquedaAvanzadaInmueble> inmuebles = servicioAvanzado.buscarConFiltrosAvanzados(ciudad, zona, precioMin, precioMax, fechaInicio, fechaFin, tienePiscina, tieneWifi);
        model.addAttribute("inmuebles", inmuebles);
        return "resultados-busqueda-avanzada";
    }
}
