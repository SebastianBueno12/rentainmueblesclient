package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.repositorio.AdministrarReservaRepositorio;
import co.ucentral.rentainmueblesclientes.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrarReservaServicio {

    @Autowired
    private AdministrarReservaRepositorio administrarReservaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Reserva> obtenerReservasPorUsuario(Long idUsuario) {
        return administrarReservaRepositorio.findByUsuarioId(idUsuario);
    }

    public void cancelarReserva(Long idReserva, Long idUsuario) {
        Reserva reserva = administrarReservaRepositorio.findById(idReserva).orElse(null);
        if (reserva != null && reserva.getUsuario().getId().equals(idUsuario)) {
            reserva.setEstadoReserva("Cancelada");
            administrarReservaRepositorio.save(reserva);
        }
    }
}
