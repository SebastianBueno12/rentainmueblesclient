package co.ucentral.rentainmueblesclientes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class BusquedaInmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String zona;

    @Column(nullable = false)
    private double precioPorNoche;

    @Column(nullable = false)
    private int maxPersonas;

    @Column(length = 1024) // Asumiendo descripciones largas
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaDisponibleDesde;

    @Column(nullable = false)
    private LocalDate fechaDisponibleHasta;

    // Getters y setters para todos los campos, incluyendo las nuevas fechas
}
