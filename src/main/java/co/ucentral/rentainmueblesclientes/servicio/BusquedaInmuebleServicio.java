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

    /**
     * Busca inmuebles basándose en varios criterios como la ciudad, fechas y número de personas.
     * Útil para el Caso de Uso 01 cuando el usuario realiza una búsqueda básica o avanzada.
     *
     * @param ciudad Ciudad del inmueble.
     * @param fechaLlegada Fecha de llegada al inmueble.
     * @param fechaSalida Fecha de salida del inmueble.
     * @param numPersonas Número de personas.
     * @return lista de inmuebles que coinciden con los criterios dados.
     */
    public List<BusquedaInmueble> buscarInmuebles(String ciudad, LocalDate fechaLlegada, LocalDate fechaSalida, Integer numPersonas) {
        return repositorio.findByCriterios(ciudad, null, fechaLlegada, fechaSalida, numPersonas);
    }

    /**
     * Obtiene los detalles de un inmueble específico, utilizada para visualizar información detallada en el CU15,
     * y para proporcionar datos al formulario de reservas en el CU02.
     *
     * @param id Identificador del inmueble.
     * @return el inmueble encontrado o null si no se encuentra.
     */
    public BusquedaInmueble obtenerDetalleInmueble(Long id) {
        Optional<BusquedaInmueble> inmueble = repositorio.findById(id);
        return inmueble.orElse(null);  // Retorna el inmueble o null si no se encuentra, maneja el caso de consulta a detalle
    }
}
