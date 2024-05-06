package co.ucentral.rentainmueblesclientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import java.time.LocalDate;
import java.util.List;

public interface BusquedaInmuebleRepositorio extends JpaRepository<BusquedaInmueble, Long> {

    // Método para buscar inmuebles por ciudad y zona, usado en el CU01 para filtrar inmuebles sin fechas o número de personas
    List<BusquedaInmueble> findByCiudadAndZona(String ciudad, String zona);

    // Método avanzado para buscar inmuebles según criterios específicos: ciudad, zona, fechas y número de personas
    @Query("SELECT i FROM BusquedaInmueble i WHERE i.ciudad = :ciudad AND i.zona = :zona AND i.fechaDisponibleDesde <= :fechaLlegada AND i.fechaDisponibleHasta >= :fechaSalida AND i.maxPersonas >= :numPersonas")
    List<BusquedaInmueble> findByCriterios(@Param("ciudad") String ciudad, @Param("zona") String zona, @Param("fechaLlegada") LocalDate fechaLlegada, @Param("fechaSalida") LocalDate fechaSalida, @Param("numPersonas") Integer numPersonas);
}
