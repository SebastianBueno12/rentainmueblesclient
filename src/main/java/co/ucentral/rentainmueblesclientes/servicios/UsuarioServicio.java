package co.ucentral.rentainmueblesclientes.servicios;



import co.ucentral.rentainmueblesclientes.Dto.UsuarioRegistroDTO;
import co.ucentral.rentainmueblesclientes.modelo.Usuario;
import java.util.List;

public interface UsuarioServicio {
    Usuario guardar(UsuarioRegistroDTO registroDTO);
    Usuario encontrarPorCorreo(String correo);

}
