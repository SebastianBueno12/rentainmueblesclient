package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Inmueble;
import co.ucentral.rentainmueblesclientes.servicio.InmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/inmuebles")
public class InmuebleControlador {

    @Autowired
    private InmuebleServicio servicio;

    @GetMapping("/buscar")
    public String buscarPorCiudadYZona(@RequestParam(required = false) String ciudad,
                                       @RequestParam(required = false) String zona, Model model) {
        if (ciudad != null && zona != null) {
            List<Inmueble> inmuebles = servicio.buscarPorCiudadYZona(ciudad, zona);
            model.addAttribute("inmuebles", inmuebles);
        }
        return "buscar-inmuebles"; // Nombre del archivo HTML creado
    }

}
