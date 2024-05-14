package co.ucentral.rentainmueblesclientes.servicios;

import co.ucentral.rentainmueblesclientes.entidad.Cliente;
import co.ucentral.rentainmueblesclientes.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepositorio.findById(id);
    }

    public void crearCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }
}