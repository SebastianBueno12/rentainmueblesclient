package co.ucentral.rentainmueblesclientes.modelo;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;

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
    private double precioPorNoche; // Precio por noche del inmueble.

    @Column(name = "max_personas", nullable = false)
    private int maxPersonas; // Capacidad máxima de personas permitidas en el inmueble.

    @Column(name = "habitaciones", nullable = false)
    private int habitaciones; // Número de habitaciones en el inmueble.

    @Column(name = "max_personas_por_habitacion", nullable = false)
    private int maxPersonasPorHabitacion; // Máximo de personas permitidas por habitación.

    @Column(length = 1024, nullable = false)
    private String descripcion; // Descripción detallada del inmueble.

    @Column(name = "fecha_disponible_desde", nullable = false)
    private LocalDate fechaDisponibleDesde; // Fecha desde cuando el inmueble está disponible.

    @Column(name = "fecha_disponible_hasta", nullable = false)
    private LocalDate fechaDisponibleHasta; // Fecha hasta cuando el inmueble está disponible.

    @Column(name = "tiene_piscina", nullable = false)
    private boolean tienePiscina; // Indica si el inmueble cuenta con piscina.

    @Column(name = "costo_adicional_piscina", nullable = true)
    private Double costoAdicionalPiscina; // Costo adicional si el inmueble tiene piscina.

    @Column(nullable = false)
    private String nombre; // Nombre del inmueble.

    @Column(nullable = false)
    private String imagenUrl; // URL de la imagen representativa del inmueble.

}
