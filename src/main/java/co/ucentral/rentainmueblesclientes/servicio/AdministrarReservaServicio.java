package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.repositorio.AdministrarReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrarReservaServicio {

    @Autowired
    private AdministrarReservaRepositorio administrarReservaRepositorio;

    public List<Reserva> obtenerReservasPorUsuarioId(Long usuarioId) {
        return administrarReservaRepositorio.findByUsuarioId(usuarioId);
    }

    public Reserva findReservaById(Long id) {
        return administrarReservaRepositorio.findById(id).orElse(null);
    }

    public Reserva saveReserva(Reserva reserva) {
        return administrarReservaRepositorio.save(reserva);
    }

    public void cancelarReserva(Long id) {
        administrarReservaRepositorio.deleteById(id);
    }
}
