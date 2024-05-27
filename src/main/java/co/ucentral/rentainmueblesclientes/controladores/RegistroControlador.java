package co.ucentral.rentainmueblesclientes.controladores;

import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    public String procesarInicioSesion(@RequestParam("username") String correo, @RequestParam("password") String clave, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioServicio.encontrarPorCorreo(correo);
        if (usuario != null && usuario.getClave().equals(clave)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/indexAutenticado";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña inválidos.");
            return "redirect:/inicioSesion";
        }
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/inicioSesion?logout";
    }

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/indexAutenticado")
    public String mostrarIndexAutenticado() {
        return "indexAutenticado";
    }

    @GetMapping("/")
    public String verPaginaDeInicio() {
        return "index";
    }
}
