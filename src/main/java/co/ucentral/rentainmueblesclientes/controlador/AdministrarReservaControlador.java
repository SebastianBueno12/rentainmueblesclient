package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.servicio.AdministrarReservaServicio;
import co.ucentral.rentainmueblesclientes.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/usuario/reservas")
public class AdministrarReservaControlador {

    @Autowired
    private AdministrarReservaServicio administrarReservaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public String listarReservasUsuario(HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return "redirect:/inicioSesion";
        }

        Usuario usuario = usuarioServicio.encontrarPorId(idUsuario);
        List<Reserva> reservas = administrarReservaServicio.obtenerReservasPorUsuario(usuario);

        model.addAttribute("reservas", reservas);
        return "reservas-usuario";
    }
}
