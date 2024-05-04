package co.ucentral.rentainmueblesclientes.repositorio;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaAvanzadaInmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusquedaAvanzadaInmuebleRepositorio extends JpaRepository<BusquedaAvanzadaInmueble, Long>, JpaSpecificationExecutor<BusquedaAvanzadaInmueble> {
    // No es necesario implementar métodos aquí las especificaciones permiten construir consultas en el servicio
}
