package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public List<Reserva> findAllReservas() {
        return reservaRepositorio.findAll();
    }

    public Reserva saveReserva(Reserva reserva) {
        return reservaRepositorio.save(reserva);
    }

    public Reserva findReservaById(Long id) {
        return reservaRepositorio.findById(id).orElse(null);
    }

    // Logica como la validacion de disponibilidad, calculo de costos, etc.
}
