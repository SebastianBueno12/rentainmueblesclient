package co.ucentral.rentainmueblesclientes.repositorio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    /**
     * CU10: Consulta personalizada para obtener las reservas de un inmueble en un rango de fechas
     * Esta consulta se utiliza para verificar la disponibilidad de un inmueble en las fechas seleccionadas
     * y se asegura de que no haya conflictos con reservas existentes.
     */
    @Query("SELECT r FROM Reserva r WHERE r.inmueble.id = :idInmueble AND " +
            "(r.fechaLlegada <= :fechaSalida AND r.fechaSalida >= :fechaLlegada)")
    List<Reserva> findReservasByInmuebleAndFechas(@Param("idInmueble") Long idInmueble,
                                                  @Param("fechaLlegada") LocalDate fechaLlegada,
                                                  @Param("fechaSalida") LocalDate fechaSalida);
}
