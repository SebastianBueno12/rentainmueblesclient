package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.servicio.ReservaServicio;
import co.ucentral.rentainmueblesclientes.servicio.BusquedaInmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private BusquedaInmuebleServicio busquedaInmuebleServicio;

    /**
     * CU02: Muestra el formulario de creación de reserva para un inmueble específico.
     */
    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion(@RequestParam("idInmueble") Long idInmueble, Model model) {
        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble);
        if (inmueble == null) {
            return "redirect:/"; // Redirecciona al inicio si el inmueble no existe
        }
        model.addAttribute("inmueble", inmueble);
        model.addAttribute("reserva", new Reserva()); // Crea una nueva instancia de reserva para ser llenada en el formulario
        return "crear-reserva"; // Retorna la vista del formulario de creación de reservas
    }

    /**
     * CU02: Procesa la creación de una nueva reserva.
     */
    @PostMapping("/crear")
    public String crearReserva(Reserva reserva, @RequestParam("idInmueble") Long idInmueble, Model model) {
        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble);
        if (inmueble == null) {
            model.addAttribute("error", "El inmueble no se encontró.");
            return "crear-reserva";
        }

        reserva.setInmueble(inmueble);

        // CU02: Verifica que el número de personas no exceda la capacidad máxima del inmueble
        if (reserva.getNumeroPersonas() > inmueble.getMaxPersonas()) {
            model.addAttribute("error", "El número de personas excede la capacidad máxima del inmueble.");
            model.addAttribute("inmueble", inmueble);
            model.addAttribute("reserva", reserva);
            return "crear-reserva";
        }

        // CU02: Verifica que las fechas seleccionadas estén dentro del rango de disponibilidad del inmueble
        if (!reserva.getFechaLlegada().isAfter(inmueble.getFechaDisponibleDesde().minusDays(1)) ||
                !reserva.getFechaSalida().isBefore(inmueble.getFechaDisponibleHasta().plusDays(1))) {
            model.addAttribute("error", "El inmueble no está disponible en las fechas seleccionadas.");
            model.addAttribute("inmueble", inmueble);
            model.addAttribute("reserva", reserva);
            return "crear-reserva";
        }

        // CU02: Guarda la nueva reserva en la base de datos
        Reserva nuevaReserva = reservaServicio.saveReserva(reserva);
        model.addAttribute("reserva", nuevaReserva);
        return "detalle-reserva"; // Retorna la vista de detalle de la reserva, mostrando confirmación y datos de la reserva
    }

    /**
     * CU10: Verifica la disponibilidad de un inmueble para un rango de fechas específico
     */
    @GetMapping("/disponibilidad")
    @ResponseBody
    public ResponseEntity<Boolean> verificarDisponibilidad(@RequestParam("idInmueble") Long idInmueble,
                                                           @RequestParam("fechaLlegada") LocalDate fechaLlegada,
                                                           @RequestParam("fechaSalida") LocalDate fechaSalida) {
        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble);
        if (inmueble == null) {
            return ResponseEntity.ok(false); // Inmueble no encontrado, por lo tanto no disponible
        }

        // CU10: Verifica si las fechas están dentro del rango de disponibilidad del inmueble
        if (!fechaLlegada.isAfter(inmueble.getFechaDisponibleDesde().minusDays(1)) ||
                !fechaSalida.isBefore(inmueble.getFechaDisponibleHasta().plusDays(1))) {
            return ResponseEntity.ok(false); // Fechas fuera del rango de disponibilidad del inmueble
        }

        // CU10: Verifica si hay reservas existentes que coincidan con las fechas seleccionadas
        List<Reserva> reservas = reservaServicio.obtenerReservasPorInmuebleYFechas(idInmueble, fechaLlegada, fechaSalida);
        boolean disponible = reservas.isEmpty();
        return ResponseEntity.ok(disponible);
    }
}
