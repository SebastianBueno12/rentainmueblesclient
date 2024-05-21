package co.ucentral.rentainmueblesclientes.controlador;

import co.ucentral.rentainmueblesclientes.modelo.Reserva;
import co.ucentral.rentainmueblesclientes.servicio.ReservaServicio;
import co.ucentral.rentainmueblesclientes.servicio.PagoSeguroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pagos")
public class PagoControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private PagoSeguroServicio pagoSeguroServicio; // Inyección del nuevo servicio de pago seguro

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

    // CU021 - Procesar el pago seguro utilizando la información de la tarjeta
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


        reserva.setEstadoReserva("Pagada");
        reservaServicio.saveReserva(reserva); // Guardar la reserva actualizada en la base de datos

        model.addAttribute("reserva", reserva);
        return "detalle-reserva"; // Retorna la vista 'detalle-reserva.html' con los detalles de la reserva
    }
}
