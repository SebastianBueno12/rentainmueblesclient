package co.ucentral.rentainmueblesclientes.servicio;

import co.ucentral.rentainmueblesclientes.Dto.UsuarioRegistroDTO;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;

public interface UsuarioServicio {
    Usuario guardar(UsuarioRegistroDTO registroDTO);
    Usuario encontrarPorCorreo(String correo);
    Usuario encontrarPorId(Long id);  // Añadir este método
    boolean procesarRecuperacionContrasena(String correo);
    boolean validarTokenRecuperacionContrasena(String token);
    boolean actualizarContrasena(String token, String clave);
}