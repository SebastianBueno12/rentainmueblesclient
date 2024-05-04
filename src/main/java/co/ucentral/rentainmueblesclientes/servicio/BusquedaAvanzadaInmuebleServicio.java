package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.modelo.BusquedaAvanzadaInmueble;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import co.ucentral.rentainmueblesclientes.repositorio.BusquedaAvanzadaInmuebleRepositorio;

@Service
public class BusquedaAvanzadaInmuebleServicio {

    @Autowired
    private BusquedaAvanzadaInmuebleRepositorio repositorioAvanzado;

    public List<BusquedaAvanzadaInmueble> buscarConFiltrosAvanzados(String ciudad, String zona, Double precioMin, Double precioMax, LocalDate fechaInicio, LocalDate fechaFin, Boolean tienePiscina, Boolean tieneWifi) {
        return repositorioAvanzado.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (ciudad != null) {
                predicates.add(criteriaBuilder.equal(root.get("ciudad"), ciudad));
            }
            if (zona != null) {
                predicates.add(criteriaBuilder.equal(root.get("zona"), zona));
            }
            if (precioMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("precioPorNoche"), precioMin));
            }
            if (precioMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("precioPorNoche"), precioMax));
            }
            if (fechaInicio != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("disponibleDesde"), fechaInicio));
            }
            if (fechaFin != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("disponibleHasta"), fechaFin));
            }
            if (tienePiscina != null) {
                predicates.add(criteriaBuilder.equal(root.get("tienePiscina"), tienePiscina));
            }
            if (tieneWifi != null) {
                predicates.add(criteriaBuilder.equal(root.get("tieneWifi"), tieneWifi));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
