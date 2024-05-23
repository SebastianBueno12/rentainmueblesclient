package co.ucentral.rentainmueblesclientes.repositorios;




import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
