package co.ucentral.rentainmueblesclientes.modelo;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id // Marca el campo como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática del valor del ID (autoincremental).
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre; // Nombre del usuario.

    @Column(nullable = false)
    private String correo; // Dirección de correo electrónico del usuario.

    @Column(nullable = false)
    private String telefono; // Número de teléfono del usuario.

    @Column(nullable = false)
    private String direccion; // Dirección física del usuario.
}
