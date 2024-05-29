package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.servicio.AdministrarReservaServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/usuario/reservas")
public class AdministrarReservaControlador {

    @Autowired
    private AdministrarReservaServicio administrarReservaServicio;

    @GetMapping
    public String verReservasUsuario(HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/inicioSesion";
        }
        List<Reserva> reservas = administrarReservaServicio.obtenerReservasPorUsuario(idUsuario);
        model.addAttribute("reservas", reservas);
        return "reservas-usuario";
    }

    @GetMapping("/cancelar")
    public String cancelarReserva(@RequestParam("idReserva") Long idReserva, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/inicioSesion";
        }
        administrarReservaServicio.cancelarReserva(idReserva, idUsuario);
        return "redirect:/usuario/reservas";
    }
}
