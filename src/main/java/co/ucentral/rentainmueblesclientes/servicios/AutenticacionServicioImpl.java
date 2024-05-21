package co.ucentral.rentainmueblesclientes.servicios;

import co.ucentral.rentainmueblesclientes.entidad.Usuario;
import co.ucentral.rentainmueblesclientes.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public AutenticacionServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public boolean autenticar(String correo, String contraseña) {
        Usuario usuario = usuarioRepositorio.findByCorreo(correo);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            return true;
        }
        return false;
    }
}

