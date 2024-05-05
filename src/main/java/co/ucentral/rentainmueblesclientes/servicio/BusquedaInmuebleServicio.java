package co.ucentral.rentainmueblesclientes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.repositorio.BusquedaInmuebleRepositorio;
import java.time.LocalDate;
import java.util.List;

@Service
public class BusquedaInmuebleServicio {

    @Autowired
    private BusquedaInmuebleRepositorio repositorio;

    public List<BusquedaInmueble> buscarInmuebles(String ciudad, LocalDate fechaLlegada, LocalDate fechaSalida, Integer numPersonas) {
        return repositorio.findByCriterios(ciudad, null, fechaLlegada, fechaSalida, numPersonas);
    }
}
