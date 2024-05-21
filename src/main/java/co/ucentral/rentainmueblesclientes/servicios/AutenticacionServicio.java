package co.ucentral.rentainmueblesclientes.servicios;

import co.ucentral.rentainmueblesclientes.entidad.Usuario;

public interface AutenticacionServicio {
    boolean autenticar(String correo, String contraseña);
}


