package co.ucentral.rentainmueblesclientes.servicio;

import org.springframework.stereotype.Service;

/**
 * Servicio para procesar pagos seguros utilizando PSE.
 *
 * Esta clase es utilizada en:
 * - CU021: Realizar pago seguro, donde se simula el procesamiento de un pago seguro utilizando PSE
 *   para una reserva específica.
 */
@Service
public class PagoSeguroServicio {

    /**
     * Simulación de procesamiento de pago seguro a través de PSE.
     *
     * @param nombreTarjeta Nombre del titular de la tarjeta.
     * @param numeroTarjeta Número de la tarjeta.
     * @param fechaExpiracion Fecha de expiración de la tarjeta.
     * @param codigoSeguridad Código de seguridad de la tarjeta.
     * @param monto Monto total a pagar.
     * @return true si el pago se procesa exitosamente, false en caso contrario.
     */
    public boolean procesarPagoSeguroPSE(String nombreTarjeta, String numeroTarjeta, String fechaExpiracion, String codigoSeguridad, double monto) {
        // Simulación de procesamiento de pago seguro a través de PSE
        return true; // Asumimos que el pago es exitoso
    }
}
