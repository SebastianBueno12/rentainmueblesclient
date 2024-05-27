package co.ucentral.rentainmueblesclientes.servicios;

import co.ucentral.rentainmueblesclientes.Dto.UsuarioRegistroDTO;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.repositorios.UsuarioRepositorio;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioServicioImplTest {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private UsuarioServicioImpl usuarioServicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarUsuario() {
        UsuarioRegistroDTO registroDTO = new UsuarioRegistroDTO("Juan", "test@correo.com", "password", "direccion", "123456789");
        Usuario usuario = new Usuario("Juan", "test@correo.com", "password", "direccion", "123456789");

        when(usuarioRepositorio.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioServicio.guardar(registroDTO);
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("test@correo.com", resultado.getCorreo());
    }

    @Test
    public void testEncontrarPorCorreo() {
        String correo = "test@correo.com";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);

        when(usuarioRepositorio.findByCorreo(correo)).thenReturn(usuario);

        Usuario resultado = usuarioServicio.encontrarPorCorreo(correo);
        assertNotNull(resultado);
        assertEquals(correo, resultado.getCorreo());
    }

    @Test
    public void testProcesarRecuperacionContrasena() throws MessagingException {
        String correo = "test@correo.com";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);

        when(usuarioRepositorio.findByCorreo(correo)).thenReturn(usuario);
        when(usuarioRepositorio.save(any(Usuario.class))).thenReturn(usuario);

        // Create a mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        doNothing().when(mailSender).send(any(MimeMessage.class));

        boolean resultado = usuarioServicio.procesarRecuperacionContrasena(correo);
        assertTrue(resultado);
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    public void testValidarTokenRecuperacionContrasena() {
        String token = "token";
        Usuario usuario = new Usuario();
        usuario.setTokenRecuperacion(token);

        when(usuarioRepositorio.findByTokenRecuperacion(token)).thenReturn(usuario);

        boolean resultado = usuarioServicio.validarTokenRecuperacionContrasena(token);
        assertTrue(resultado);
    }

    @Test
    public void testActualizarContrasena() {
        String token = "token";
        String clave = "nuevaClave";
        Usuario usuario = new Usuario();
        usuario.setTokenRecuperacion(token);

        when(usuarioRepositorio.findByTokenRecuperacion(token)).thenReturn(usuario);

        boolean resultado = usuarioServicio.actualizarContrasena(token, clave);
        assertTrue(resultado);
        verify(usuarioRepositorio, times(1)).save(usuario);
    }
}
