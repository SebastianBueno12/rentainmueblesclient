package co.ucentral.rentainmueblesclientes.modelo;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Clase que representa un inmueble disponible para alquiler.
 * Se utiliza para la realización de reservas (CU02),
 * donde se requiere acceso a detalles completos del inmueble para verificar disponibilidad, calcular costos,
 * y proporcionar información detallada a los usuarios.
 */
@Data
@Entity
public class BusquedaInmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String zona;

    @Column(name = "precio_por_noche", nullable = false)
    private double precioPorNoche; // Utilizado para calcular el costo total de la reserva.

    @Column(name = "max_personas", nullable = false)
    private int maxPersonas; // Capacidad máxima, crucial para validar la cantidad de huéspedes.

    @Column(name = "habitaciones", nullable = false)
    private int habitaciones;

    @Column(name = "banos", nullable = false)
    private int banos;

    @Column(name = "max_personas_por_habitacion", nullable = false)
    private int maxPersonasPorHabitacion;

    @Column(length = 1024, nullable = false)
    private String descripcion;

    @Column(name = "fecha_disponible_desde", nullable = false)
    private LocalDate fechaDisponibleDesde; // Fechas disponibles para reservar el inmueble.

    @Column(name = "fecha_disponible_hasta", nullable = false)
    private LocalDate fechaDisponibleHasta;

    @Column(name = "tiene_piscina", nullable = false)
    private boolean tienePiscina;

    @Column(name = "costo_adicional_piscina", nullable = true)
    private Double costoAdicionalPiscina;

    @Column(nullable = false)
    private String nombre; // Nombre del inmueble.

    @Column(nullable = false)
    private String imagenUrl; // Imagen representativa del inmueble.

    @Column(nullable = false)
    private String politicaCancelacion; // Política de cancelación aplicable.
}
