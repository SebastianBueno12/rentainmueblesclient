package co.ucentral.rentainmueblesclientes.controladores;

import co.ucentral.rentainmueblesclientes.controlador.BusquedaInmuebleControlador;
import co.ucentral.rentainmueblesclientes.repositorio.BusquedaInmuebleRepositorio;
import co.ucentral.rentainmueblesclientes.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecuperacionContrasenaControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public RecuperacionContrasenaControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/recuperarContrasena")
    public String mostrarFormularioRecuperarContrasena() {
        return "recuperarContrasena";
    }

    @PostMapping("/recuperarContrasena")
    public String procesarRecuperacionContrasena(@RequestParam("correo") String correo, RedirectAttributes redirectAttributes) {
        boolean resultado = usuarioServicio.procesarRecuperacionContrasena(correo);
        if (resultado) {
            redirectAttributes.addFlashAttribute("mensaje", "Se ha enviado un enlace de recuperación a su correo electrónico.");
        } else {
            redirectAttributes.addFlashAttribute("error", "La dirección de correo electrónico no está registrada.");
        }
        return "redirect:/recuperarContrasena";
    }

    @GetMapping("/restablecerContrasena")
    public String mostrarFormularioRestablecerContrasena(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        boolean esValido = usuarioServicio.validarTokenRecuperacionContrasena(token);
        if (esValido) {
            redirectAttributes.addFlashAttribute("token", token);
            return "restablecerContrasena";
        } else {
            redirectAttributes.addFlashAttribute("error", "El enlace de recuperación de contraseña es inválido o ha expirado.");
            return "redirect:/recuperarContrasena";
        }
    }

    @PostMapping("/restablecerContrasena")
    public String procesarRestablecimientoContrasena(@RequestParam("token") String token, @RequestParam("clave") String clave, @RequestParam("confirmarClave") String confirmarClave, RedirectAttributes redirectAttributes) {
        if (!clave.equals(confirmarClave)) {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden.");
            return "redirect:/restablecerContrasena?token=" + token;
        }
        boolean resultado = usuarioServicio.actualizarContrasena(token, clave);
        if (resultado) {
            redirectAttributes.addFlashAttribute("mensaje", "Su contraseña ha sido restablecida con éxito.");
            return "redirect:/inicioSesion";
        } else {
            redirectAttributes.addFlashAttribute("error", "El enlace de recuperación de contraseña es inválido o ha expirado.");
            return "redirect:/recuperarContrasena";
        }
    }
}