package co.ucentral.rentainmueblesclientes.controladores;

import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public RegistroControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/inicioSesion")
    public String iniciarSesion() {
        return "inicioSesion";
    }

    @PostMapping("/inicioSesion")
    public String procesarInicioSesion(@RequestParam("username") String correo, @RequestParam("password") String clave, HttpSession session) {
        Usuario usuario = usuarioServicio.encontrarPorCorreo(correo);
        if (usuario != null && usuario.getClave().equals(clave)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/index";
        } else {
            return "redirect:/inicioSesion?error";
        }
    }

    @GetMapping("/index")
    public String mostrarIndex(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/inicioSesion";
        }
        return "index";
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/inicioSesion";
    }

    @GetMapping("/")
    public String verPaginaDeInicio() {
        return "redirect:/inicioSesion";
    }
}
