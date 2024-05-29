package co.ucentral.rentainmueblesclientes.repositorio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrarReservaRepositorio extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long idUsuario);
}
