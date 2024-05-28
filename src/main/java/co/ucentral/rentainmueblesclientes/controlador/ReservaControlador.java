package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.servicio.ReservaServicio;
import co.ucentral.rentainmueblesclientes.servicio.BusquedaInmuebleServicio;
import co.ucentral.rentainmueblesclientes.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private BusquedaInmuebleServicio busquedaInmuebleServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion(@RequestParam("idInmueble") Long idInmueble, HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/inicioSesion";
        }

        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble);
        Usuario usuario = usuarioServicio.encontrarPorId(idUsuario);

        if (inmueble == null || usuario == null) {
            return "redirect:/"; // Redirecciona al inicio si el inmueble o el usuario no existen
        }

        model.addAttribute("inmueble", inmueble);
        model.addAttribute("usuario", usuario);
        model.addAttribute("reserva", new Reserva()); // Crea una nueva instancia de reserva para ser llenada en el formulario
        return "crear-reserva"; // Retorna la vista del formulario de creación de reservas
    }

    @PostMapping("/crear")
    public String crearReserva(Reserva reserva, @RequestParam("idInmueble") Long idInmueble, HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/inicioSesion";
        }

        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble);
        Usuario usuario = usuarioServicio.encontrarPorId(idUsuario);

        if (inmueble == null) {
            model.addAttribute("error", "El inmueble no se encontró.");
            return "crear-reserva";
        }

        if (usuario == null) {
            model.addAttribute("error", "El usuario no se encontró.");
            return "crear-reserva";
        }

        reserva.setInmueble(inmueble);
        reserva.setUsuario(usuario);

        if (reserva.getNumeroPersonas() > inmueble.getMaxPersonas()) {
            model.addAttribute("error", "El número de personas excede la capacidad máxima del inmueble.");
            model.addAttribute("inmueble", inmueble);
            model.addAttribute("reserva", reserva);
            return "crear-reserva";
        }

        if (!reserva.getFechaLlegada().isAfter(inmueble.getFechaDisponibleDesde().minusDays(1)) ||
                !reserva.getFechaSalida().isBefore(inmueble.getFechaDisponibleHasta().plusDays(1))) {
            model.addAttribute("error", "El inmueble no está disponible en las fechas seleccionadas.");
            model.addAttribute("inmueble", inmueble);
            model.addAttribute("reserva", reserva);
            return "crear-reserva";
        }

        reserva.setEstadoReserva("pendiente");

        Reserva nuevaReserva = reservaServicio.saveReserva(reserva);
        model.addAttribute("reserva", nuevaReserva);

        // Redirigir al formulario de pago
        return "redirect:/pagos/formulario?idReserva=" + nuevaReserva.getIdReserva();
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Boolean> verificarDisponibilidad(@RequestParam("idInmueble") Long idInmueble,
                                                           @RequestParam("fechaLlegada") LocalDate fechaLlegada,
                                                           @RequestParam("fechaSalida") LocalDate fechaSalida) {
        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble);
        if (inmueble == null) {
            return ResponseEntity.ok(false); // Inmueble no encontrado, por lo tanto no disponible
        }

        if (!fechaLlegada.isAfter(inmueble.getFechaDisponibleDesde().minusDays(1)) ||
                !fechaSalida.isBefore(inmueble.getFechaDisponibleHasta().plusDays(1))) {
            return ResponseEntity.ok(false); // Fechas fuera del rango de disponibilidad del inmueble
        }

        List<Reserva> reservas = reservaServicio.obtenerReservasPorInmuebleYFechas(idInmueble, fechaLlegada, fechaSalida);
        boolean disponible = reservas.isEmpty();
        return ResponseEntity.ok(disponible);
    }
}
