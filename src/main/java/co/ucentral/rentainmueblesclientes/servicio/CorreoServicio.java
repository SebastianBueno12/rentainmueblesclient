package co.ucentral.rentainmueblesclientes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio para enviar correos electrónicos.
 *
 * Esta clase es utilizada en:
 * - CU018: Enviar confirmación de reserva.
 */
@Service
public class CorreoServicio {

    @Autowired
    private JavaMailSender mailSender;
    public void enviarCorreo(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
