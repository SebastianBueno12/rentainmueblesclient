package co.ucentral.rentainmueblesclientes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
public class BusquedaAvanzadaInmueble {
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

    @Column(length = 1024) // Descripciones largas
    private String descripcion;

    @Column
    private LocalDate disponibleDesde;

    @Column
    private LocalDate disponibleHasta;

    @Column
    private boolean tienePiscina;

    @Column
    private boolean tieneWifi;

    // Getters y setters
}
