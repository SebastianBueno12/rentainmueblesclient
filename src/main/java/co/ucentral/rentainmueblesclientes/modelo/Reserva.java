package co.ucentral.rentainmueblesclientes.modelo;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Clase que representa una reserva de un inmueble.
 *
 * Esta clase es utilizada en:
 * - CU02: Realización de reservas, donde se almacenan los detalles de la reserva, como fechas, número de personas, servicios adicionales, y costo total.
 * - CU021: Realización de pago seguro, donde se requiere actualizar el estado de la reserva después de un pago exitoso.
 */
@Data
@Entity
@Table(name = "reservas")
public class Reserva {
    @Id // Marca el campo como la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática del valor del ID (autoincremental)
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario") // Define la columna de unión para la relación con la entidad Usuario
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble") // Define la columna de unión para la relación con la entidad BusquedaInmueble
    private BusquedaInmueble inmueble;

    @Column(nullable = false)
    private LocalDate fechaLlegada; // Fecha de llegada para la reserva

    @Column(nullable = false)
    private LocalDate fechaSalida; // Fecha de salida para la reserva

    @Column(nullable = false)
    private int numeroPersonas; // Número de personas incluidas en la reserva

    @Column
    private String serviciosAdicionales; // Servicios adicionales solicitados en la reserva

    @Column(nullable = false)
    private double costoTotal; // Costo total calculado de la reserva

    @Column(nullable = false)
    private String estadoReserva; // Estado actual de la reserva (p.ej., confirmada, cancelada)
}
