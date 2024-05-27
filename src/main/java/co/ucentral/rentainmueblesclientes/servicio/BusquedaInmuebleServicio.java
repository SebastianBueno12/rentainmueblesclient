package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaInmueble;
import co.ucentral.rentainmueblesclientes.repositorio.BusquedaInmuebleRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusquedaInmuebleServicio {

    @Autowired
    private BusquedaInmuebleRepositorio repositorio;

    public List<BusquedaInmueble> buscarInmuebles(String ciudad, LocalDate fechaLlegada, LocalDate fechaSalida, Integer numPersonas,
                                                  String zona, Boolean tienePiscina, Double precioMinimo, Double precioMaximo) {
        try {
            return repositorio.findByCriterios(ciudad, zona, fechaLlegada, fechaSalida, numPersonas, tienePiscina, precioMinimo, precioMaximo);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir las fechas", e);
        }
    }

    public BusquedaInmueble obtenerDetalleInmueble(Long id) {
        return repositorio.findById(id).orElse(null);
    }
}
