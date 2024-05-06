package co.ucentral.rentainmueblesclientes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.repositorio.BusquedaInmuebleRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BusquedaInmuebleServicio {

    @Autowired
    private BusquedaInmuebleRepositorio repositorio;

    // Busca inmuebles según criterios definidos, útil para el CU01 cuando el usuario realiza una búsqueda básica o avanzada
    public List<BusquedaInmueble> buscarInmuebles(String ciudad, LocalDate fechaLlegada, LocalDate fechaSalida, Integer numPersonas) {
        return repositorio.findByCriterios(ciudad, null, fechaLlegada, fechaSalida, numPersonas);
    }

    // Obtiene el detalle de un inmueble específico, implementación del CU15 para visualizar información detallada
    public BusquedaInmueble obtenerDetalleInmueble(Long id) {
        Optional<BusquedaInmueble> inmueble = repositorio.findById(id);
        return inmueble.orElse(null);  // Retorna el inmueble o null si no se encuentra, maneja el caso de consulta a detalle
    }
}
