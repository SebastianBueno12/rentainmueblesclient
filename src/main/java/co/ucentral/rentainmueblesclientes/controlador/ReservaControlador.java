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
    private ReservaServicio reservaServicio;

    @Autowired
    private BusquedaInmuebleServicio busquedaInmuebleServicio;

    // Método GET para mostrar el formulario de creación de reserva
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

    // Método POST para procesar la creación de una nueva reserva
    @PostMapping("/crear")
    public String crearReserva(Reserva reserva, Model model) {
        Reserva nuevaReserva = reservaServicio.saveReserva(reserva); // Guarda la reserva en la base de datos
        model.addAttribute("reserva", nuevaReserva); // Agrega la nueva reserva al modelo
        return "detalle-reserva"; // Retorna la vista de detalle de la reserva, incluyendo confirmación y datos de la reserva
    }
}
