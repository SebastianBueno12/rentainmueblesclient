package co.ucentral.rentainmueblesclientes.repositorio;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
}
