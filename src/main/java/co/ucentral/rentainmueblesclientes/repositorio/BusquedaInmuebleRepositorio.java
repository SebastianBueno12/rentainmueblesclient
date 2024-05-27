package co.ucentral.rentainmueblesclientes.repositorio;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusquedaInmuebleRepositorio extends CrudRepository<BusquedaInmueble, Long> {

    @Query("SELECT i FROM BusquedaInmueble i WHERE i.ciudad = :ciudad AND " +
            "(:zona IS NULL OR :zona = '' OR i.zona = :zona) AND " +
            "(:fechaLlegada IS NULL OR i.fechaDisponibleDesde <= :fechaLlegada) AND " +
            "(:fechaSalida IS NULL OR i.fechaDisponibleHasta >= :fechaSalida) AND " +
            "(:numPersonas IS NULL OR i.maxPersonas >= :numPersonas) AND " +
            "(:tienePiscina IS NULL OR i.tienePiscina = :tienePiscina) AND " +
            "(:precioMinimo IS NULL OR i.precioPorNoche >= :precioMinimo) AND " +
            "(:precioMaximo IS NULL OR i.precioPorNoche <= :precioMaximo)")
    List<BusquedaInmueble> findByCriterios(@Param("ciudad") String ciudad, @Param("zona") String zona,
                                           @Param("fechaLlegada") LocalDate fechaLlegada, @Param("fechaSalida") LocalDate fechaSalida,
                                           @Param("numPersonas") Integer numPersonas, @Param("tienePiscina") Boolean tienePiscina,
                                           @Param("precioMinimo") Double precioMinimo, @Param("precioMaximo") Double precioMaximo);
}
