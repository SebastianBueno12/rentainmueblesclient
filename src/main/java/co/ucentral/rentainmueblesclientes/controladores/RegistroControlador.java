package co.ucentral.rentainmueblesclientes.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroControlador {

    @GetMapping("/inicioSesion")
    public String iniciarSesion() {
        return "inicioSesion";
    }

    @PostMapping("/inicioSesion")
    public String procesarInicioSesion() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/")
    public String verPaginaDeInicio() {
        return "inicioSesion";
    }
}
