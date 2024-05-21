package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.servicio.ReservaServicio;
import co.ucentral.rentainmueblesclientes.servicio.PagoSeguroServicio;
import co.ucentral.rentainmueblesclientes.servicio.CorreoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para gestionar los pagos.
 *
 * Esta clase es utilizada en:
 * - CU021: Realizar pago seguro utilizando PSE.
 * - CU018: Enviar confirmación de reserva.
 */
@Controller
@RequestMapping("/pagos")
public class PagoControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private PagoSeguroServicio pagoSeguroServicio; // Inyección del nuevo servicio de pago seguro

    @Autowired
    private CorreoServicio correoServicio; // Inyección del servicio de correo

    // CU021 - Mostrar el formulario de pago para una reserva específica
    @GetMapping("/formulario")
    public String mostrarFormularioDePago(@RequestParam("idReserva") Long idReserva, Model model) {
        Reserva reserva = reservaServicio.findReservaById(idReserva);
        if (reserva == null) {
            model.addAttribute("error", "La reserva no se encontró.");
            return "error";
        }
        model.addAttribute("reserva", reserva);
        return "pago"; // Retorna la vista 'pago.html'
    }

    // CU021 y CU018 - Procesar el pago seguro y enviar confirmación por correo
    @PostMapping("/procesar")
    public String realizarPagoSeguro(@RequestParam("idReserva") Long idReserva,
                                     @RequestParam("nombreTarjeta") String nombreTarjeta,
                                     @RequestParam("numeroTarjeta") String numeroTarjeta,
                                     @RequestParam("fechaExpiracion") String fechaExpiracion,
                                     @RequestParam("codigoSeguridad") String codigoSeguridad,
                                     Model model) {
        Reserva reserva = reservaServicio.findReservaById(idReserva);
        if (reserva == null) {
            model.addAttribute("error", "La reserva no se encontró.");
            return "pago"; // Retorna la vista 'pago.html' con un mensaje de error
        }

        // Llamada al servicio de pago para procesar el pago seguro
        boolean pagoExitoso = pagoSeguroServicio.procesarPagoSeguroPSE(nombreTarjeta, numeroTarjeta, fechaExpiracion, codigoSeguridad, reserva.getCostoTotal());

        if (!pagoExitoso) {
            model.addAttribute("error", "El pago no se pudo realizar. Por favor, inténtelo de nuevo.");
            return "pago"; // Retorna la vista 'pago.html' con un mensaje de error
        }

        // CU019 - Actualizar el estado de la reserva a 'Pagada' después de un pago exitoso
        reserva.setEstadoReserva("Pagada");
        reservaServicio.saveReserva(reserva); // Guardar la reserva actualizada en la base de datos

        // CU018 - Enviar confirmación de la reserva por correo electrónico
        String asunto = "Confirmación de Reserva";
        String mensaje = "Su reserva ha sido confirmada. Detalles de la reserva:\n" +
                "Inmueble: " + reserva.getInmueble().getNombre() + "\n" +
                "Fecha de llegada: " + reserva.getFechaLlegada() + "\n" +
                "Fecha de salida: " + reserva.getFechaSalida() + "\n" +
                "Costo total: $" + reserva.getCostoTotal() + "\n" +
                "Gracias por reservar con nosotros.";
        correoServicio.enviarCorreo(reserva.getUsuario().getCorreo(), asunto, mensaje);

        model.addAttribute("reserva", reserva);
        return "detalle-reserva"; // Retorna la vista de detalle de la reserva, mostrando confirmación y datos de la reserva
    }
}
