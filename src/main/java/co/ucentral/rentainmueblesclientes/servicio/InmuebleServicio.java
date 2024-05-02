package co.ucentral.rentainmueblesclientes.servicio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import co.ucentral.rentainmueblesclientes.repositorio.InmuebleRepositorio;
import co.ucentral.rentainmueblesclientes.modelo.Inmueble;
import java.util.List;

@Service
public class InmuebleServicio {

    @Autowired
    private InmuebleRepositorio repositorio;

    public List<Inmueble> buscarPorCiudadYZona(String ciudad, String zona) {
        return repositorio.findByCiudadAndZona(ciudad, zona);
    }
}
