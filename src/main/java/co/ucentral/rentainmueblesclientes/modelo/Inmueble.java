package co.ucentral.rentainmueblesclientes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Inmueble {
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

    // Getters y setters
}
