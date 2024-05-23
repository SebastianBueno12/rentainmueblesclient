package co.ucentral.rentainmueblesclientes.servicios;

import co.ucentral.rentainmueblesclientes.modelo.Usuario;
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
    public boolean autenticar(String correo, String clave) {
        Usuario usuario = usuarioRepositorio.findByCorreo(correo);
                    if (usuario != null && usuario.getClave().equals(clave)) {
            return true;
        }
        return false;
    }
}

