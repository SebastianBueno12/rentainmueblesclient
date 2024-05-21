package co.ucentral.rentainmueblesclientes.servicios;


import co.ucentral.rentainmueblesclientes.Dto.UsuarioRegistroDTO;
import co.ucentral.rentainmueblesclientes.entidad.Usuario;
import co.ucentral.rentainmueblesclientes.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;


    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                                        registroDTO.getCorreo(),
                                        registroDTO.getContraseña());
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario encontrarPorCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }
}
