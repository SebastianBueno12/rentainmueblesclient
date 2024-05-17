package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.servicio.ReservaServicio;
import co.ucentral.rentainmueblesclientes.servicio.BusquedaInmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio; // Inyección de dependencia del servicio de reserva

    @Autowired
    private BusquedaInmuebleServicio busquedaInmuebleServicio; // Inyección de dependencia del servicio de búsqueda de inmuebles

    // Método que lista todas las reservas existentes
    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaServicio.findAllReservas()); // Carga la lista de reservas en el modelo
        return "lista-reservas"; // Retorna la vista de lista de reservas
    }

    // Método GET para mostrar el formulario de creación de reserva
    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion(@RequestParam("idInmueble") Long idInmueble, Model model) {
        BusquedaInmueble inmueble = busquedaInmuebleServicio.obtenerDetalleInmueble(idInmueble); // Obtención del inmueble por ID
        if (inmueble == null) {
            return "redirect:/"; // Redirecciona al inicio si el inmueble no existe
        }
        model.addAttribute("inmueble", inmueble); // Agrega el inmueble al modelo
        model.addAttribute("reserva", new Reserva()); // Agrega un objeto reserva nuevo al modelo
        return "crear-reserva"; // Retorna la vista del formulario de creación de reservas
    }

    // Método POST para crear una nueva reserva
    @PostMapping("/crear")
    public String crearReserva(Reserva reserva, Model model) {
        Reserva nuevaReserva = reservaServicio.saveReserva(reserva); // Guarda la reserva en la base de datos
        model.addAttribute("reserva", nuevaReserva); // Agrega la nueva reserva al modelo
        return "detalle-reserva"; // Retorna la vista de detalle de la reserva
    }
}
