package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio para gestionar las reservas de inmuebles.
 *
 * Esta clase es utilizada en:
 * - CU02: Realización de reservas, donde se manejan las operaciones CRUD para las reservas.
 * - CU10: Calcular costo total de reserva, donde se verifica la disponibilidad de un inmueble en un rango de fechas
 *   y se asegura de que no haya conflictos con reservas existentes.
 */

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    /**
     * CU02: Obtiene todas las reservas.
     */
    public List<Reserva> findAllReservas() {
        return reservaRepositorio.findAll();
    }

    /**
     * CU02: Guarda una nueva reserva en la base de datos.
     */
    public Reserva saveReserva(Reserva reserva) {
        return reservaRepositorio.save(reserva);
    }

    /**
     * CU02: Encuentra una reserva por su ID.
     */
    public Reserva findReservaById(Long id) {
        return reservaRepositorio.findById(id).orElse(null);
    }

    /**
     * CU10: Obtiene las reservas de un inmueble en un rango de fechas.
     */
    public List<Reserva> obtenerReservasPorInmuebleYFechas(Long idInmueble, LocalDate fechaLlegada, LocalDate fechaSalida) {
        return reservaRepositorio.findReservasByInmuebleAndFechas(idInmueble, fechaLlegada, fechaSalida);
    }
}