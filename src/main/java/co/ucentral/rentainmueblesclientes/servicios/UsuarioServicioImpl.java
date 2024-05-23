package co.ucentral.rentainmueblesclientes.servicios;


import co.ucentral.rentainmueblesclientes.Dto.UsuarioRegistroDTO;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import co.ucentral.rentainmueblesclientes.repositorios.UsuarioRepositorio;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final JavaMailSender mailSender;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, JavaMailSender mailSender) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.mailSender = mailSender;
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(
                registroDTO.getNombre(),
                registroDTO.getCorreo(),
                registroDTO.getClave(),
                registroDTO.getDireccion(),
                registroDTO.getTelefono()
        );
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario encontrarPorCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo);
    }

    @Override
    public boolean procesarRecuperacionContrasena(String correo) {
        Usuario usuario = usuarioRepositorio.findByCorreo(correo);
        if (usuario != null) {
            String token = UUID.randomUUID().toString();
            usuario.setTokenRecuperacion(token);
            usuarioRepositorio.save(usuario);
            enviarCorreoRecuperacionContrasena(usuario.getCorreo(), token);
            return true;
        }
        return false;
    }

    @Override
    public boolean validarTokenRecuperacionContrasena(String token) {
        Usuario usuario = usuarioRepositorio.findByTokenRecuperacion(token);
        return usuario != null;
    }

    @Override
    @Transactional
    public boolean actualizarContrasena(String token, String clave) {
        Usuario usuario = usuarioRepositorio.findByTokenRecuperacion(token);
        if (usuario != null) {
            usuario.setClave(clave);
            usuario.setTokenRecuperacion(null);
            usuarioRepositorio.save(usuario);
            return true;
        }
        return false;
    }

    private void enviarCorreoRecuperacionContrasena(String correo, String token) {
        String urlRestablecer = "http://localhost:8862/restablecerContrasena?token=" + token;
        String asunto = "Recuperación de contraseña";
        String cuerpo = "Para restablecer su contraseña, haga clic en el siguiente enlace:\n" + urlRestablecer;

        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setTo(correo);
            helper.setSubject(asunto);
            helper.setText(cuerpo, true);
            mailSender.send(mensaje);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
