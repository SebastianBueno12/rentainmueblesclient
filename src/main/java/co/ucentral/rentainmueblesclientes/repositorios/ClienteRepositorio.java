package co.ucentral.rentainmueblesclientes.repositorios;

import co.ucentral.rentainmueblesclientes.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
