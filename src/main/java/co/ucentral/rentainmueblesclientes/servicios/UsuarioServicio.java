package co.ucentral.rentainmueblesclientes.servicios;


import co.ucentral.rentainmueblesclientes.Dto.UsuarioRegistroDTO;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;


public interface UsuarioServicio {
    Usuario guardar(UsuarioRegistroDTO registroDTO);
    Usuario encontrarPorCorreo(String correo);
    boolean procesarRecuperacionContrasena(String correo);
    boolean validarTokenRecuperacionContrasena(String token);
    boolean actualizarContrasena(String token, String clave);
}

