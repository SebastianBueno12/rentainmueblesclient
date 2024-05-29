package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.repositorio.AdministrarReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrarReservaServicio {

    @Autowired
    private AdministrarReservaRepositorio administrarReservaRepositorio;

    public List<Reserva> obtenerReservasPorUsuario(Usuario usuario) {
        return administrarReservaRepositorio.findByUsuario(usuario);
    }
}