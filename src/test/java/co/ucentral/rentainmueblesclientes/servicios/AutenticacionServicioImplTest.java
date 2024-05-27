package co.ucentral.rentainmueblesclientes.servicios;

import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.repositorios.UsuarioRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutenticacionServicioImplTest {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @InjectMocks
    private AutenticacionServicioImpl autenticacionServicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAutenticarUsuarioValido() {
        String correo = "test@correo.com";
        String clave = "password";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setClave(clave);

        when(usuarioRepositorio.findByCorreo(correo)).thenReturn(usuario);

        boolean resultado = autenticacionServicio.autenticar(correo, clave);
        assertTrue(resultado);
    }

    @Test
    public void testAutenticarUsuarioInvalido() {
        String correo = "test@correo.com";
        String clave = "password";

        when(usuarioRepositorio.findByCorreo(correo)).thenReturn(null);

        boolean resultado = autenticacionServicio.autenticar(correo, clave);
        assertFalse(resultado);
    }

    @Test
    public void testAutenticarClaveIncorrecta() {
        String correo = "test@correo.com";
        String clave = "password";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setClave("otraClave");

        when(usuarioRepositorio.findByCorreo(correo)).thenReturn(usuario);

        boolean resultado = autenticacionServicio.autenticar(correo, clave);
        assertFalse(resultado);
    }
}