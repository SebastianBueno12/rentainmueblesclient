package co.ucentral.rentainmueblesclientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import co.ucentral.rentainmueblesclientes.modelo.Inmueble;
import java.util.List;

public interface InmuebleRepositorio extends JpaRepository<Inmueble, Long> {
    List<Inmueble> findByCiudadAndZona(String ciudad, String zona);
}
