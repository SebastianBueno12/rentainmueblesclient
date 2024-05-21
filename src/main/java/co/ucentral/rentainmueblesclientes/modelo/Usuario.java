package co.ucentral.rentainmueblesclientes.modelo;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Clase que representa a un usuario del sistema.
 *
 * Esta clase es utilizada en:
 * - CU08: Registrar usuario, donde se almacenan los detalles de los usuarios registrados en el sistema.
 * - CU02: Realización de reservas, donde se asocia un usuario a una reserva específica.
 */
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
