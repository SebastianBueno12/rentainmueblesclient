package co.ucentral.rentainmueblesclientes.repositorios;

import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositorioTest {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void setUp() {
        usuarioRepositorio.deleteAll();
    }

    @Test
    public void whenFindByCorreo_thenReturnUsuario() {
        // given
        String correo = "test" + UUID.randomUUID().toString() + "@correo.com";
        Usuario usuario = new Usuario("Test", correo, "password", "Direccion", "1234567890");
        usuarioRepositorio.save(usuario);

        // when
        Usuario encontrado = usuarioRepositorio.findByCorreo(usuario.getCorreo());

        // then
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getCorreo()).isEqualTo(usuario.getCorreo());
    }

    @Test
    public void whenFindByTokenRecuperacion_thenReturnUsuario() {
        // given
        String correo = "test" + UUID.randomUUID().toString() + "@correo.com";
        Usuario usuario = new Usuario("Test", correo, "password", "Direccion", "1234567890");
        String token = UUID.randomUUID().toString();
        usuario.setTokenRecuperacion(token);
        usuarioRepositorio.save(usuario);

        // when
        Usuario encontrado = usuarioRepositorio.findByTokenRecuperacion(token);

        // then
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getTokenRecuperacion()).isEqualTo(token);
    }

    @Test
    public void whenInvalidCorreo_thenReturnNull() {
        // when
        Usuario encontrado = usuarioRepositorio.findByCorreo("wrong@correo.com");

        // then
        assertThat(encontrado).isNull();
    }

    @Test
    public void whenInvalidToken_thenReturnNull() {
        // when
        Usuario encontrado = usuarioRepositorio.findByTokenRecuperacion("invalid-token");

        // then
        assertThat(encontrado).isNull();
    }
}
