package co.ucentral.rentainmueblesclientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import java.time.LocalDate;
import java.util.List;

public interface BusquedaInmuebleRepositorio extends JpaRepository<BusquedaInmueble, Long> {

    /**
     * Método para buscar inmuebles por ciudad y zona, usado en el CU01 para filtrar inmuebles sin fechas o número de personas.
     * Este método apoya el proceso de selección inicial de inmuebles antes de realizar una reserva.
     */
    List<BusquedaInmueble> findByCiudadAndZona(String ciudad, String zona);

    /**
     * Método avanzado para buscar inmuebles según criterios específicos: ciudad, zona, fechas y número de personas.
     * Este método es crucial tanto para el CU01 en la búsqueda avanzada de inmuebles como para el CU02,
     * donde se verifica la disponibilidad del inmueble antes de confirmar una reserva.
     */
    @Query("SELECT i FROM BusquedaInmueble i WHERE i.ciudad = :ciudad AND i.zona = :zona AND i.fechaDisponibleDesde <= :fechaLlegada AND i.fechaDisponibleHasta >= :fechaSalida AND i.maxPersonas >= :numPersonas")
    List<BusquedaInmueble> findByCriterios(@Param("ciudad") String ciudad, @Param("zona") String zona, @Param("fechaLlegada") LocalDate fechaLlegada, @Param("fechaSalida") LocalDate fechaSalida, @Param("numPersonas") Integer numPersonas);
}
