package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.servicio.PoliticasCancelacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliticasCancelacionControlador {

    @Autowired
    private PoliticasCancelacionServicio politicasCancelacionServicio;

    @GetMapping("/politicas-cancelacion")
    public String mostrarPoliticas(Model model) {
        String politicas = politicasCancelacionServicio.obtenerPoliticas();
        model.addAttribute("politicasCancelacion", politicas);
        return "politicas-cancelacion";
    }
}
