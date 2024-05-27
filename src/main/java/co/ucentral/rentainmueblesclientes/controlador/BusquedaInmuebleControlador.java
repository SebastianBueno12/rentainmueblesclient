package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.servicio.BusquedaInmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            List<BusquedaInmueble> busquedaInmuebles = servicio.buscarInmuebles(ciudad, fechaLlegada, fechaSalida, numPersonas, null, null, null, null);
            if (busquedaInmuebles.isEmpty()) {
                model.addAttribute("message", "No se encontraron inmuebles que coincidan con los criterios de búsqueda.");
            } else {
                model.addAttribute("inmuebles", busquedaInmuebles);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la búsqueda de inmuebles", e);
        }
        return "buscar-inmuebles";
    }

    @GetMapping("/filtrar")
    public String filtrarInmuebles(
            @RequestParam String ciudad,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
            @RequestParam(required = false) Integer numPersonas,
            @RequestParam(required = false) String zona,
            @RequestParam(required = false) String tienePiscina,
            @RequestParam(required = false) Double precioMinimo,
            @RequestParam(required = false) Double precioMaximo,
            Model model) {
        try {
            Boolean tienePiscinaBool = null;
            if (tienePiscina != null && !tienePiscina.isEmpty()) {
                tienePiscinaBool = Boolean.parseBoolean(tienePiscina);
            }
            List<BusquedaInmueble> busquedaInmuebles = servicio.buscarInmuebles(ciudad, fechaLlegada, fechaSalida, numPersonas, zona, tienePiscinaBool, precioMinimo, precioMaximo);
            if (busquedaInmuebles.isEmpty()) {
                model.addAttribute("message", "No se encontraron inmuebles que coincidan con los criterios de búsqueda.");
            } else {
                model.addAttribute("inmuebles", busquedaInmuebles);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la búsqueda de inmuebles", e);
        }
        return "buscar-inmuebles";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalleInmueble(@PathVariable Long id, Model model) {
        try {
            BusquedaInmueble inmueble = servicio.obtenerDetalleInmueble(id);
            if (inmueble == null) {
                model.addAttribute("message", "El inmueble solicitado no está disponible.");
                return "error";
            }
            model.addAttribute("inmueble", inmueble);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inmueble no encontrado", e);
        }
        return "detalle-inmueble";
    }
}
