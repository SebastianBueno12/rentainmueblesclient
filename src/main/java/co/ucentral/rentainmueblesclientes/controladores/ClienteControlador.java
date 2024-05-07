package co.ucentral.rentainmueblesclientes.controladores;


import co.ucentral.rentainmueblesclientes.entidad.Cliente;
import co.ucentral.rentainmueblesclientes.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", clienteServicio.obtenerTodosLosClientes());
        return "listaClientes";
    }

    @GetMapping("/clientes/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registroCliente";
    }

    @PostMapping("/clientes/registro")
    public String registrarCliente(Cliente cliente) {
        clienteServicio.crearCliente(cliente);
        return "redirect:/clientes";
    }
}
