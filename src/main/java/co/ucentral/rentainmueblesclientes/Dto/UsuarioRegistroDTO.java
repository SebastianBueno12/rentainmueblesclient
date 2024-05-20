package co.ucentral.rentainmueblesclientes.Dto;


public class UsuarioRegistroDTO {

    private String nombre;
    private String correo;
    private String contraseña;


    public UsuarioRegistroDTO() {}


    public UsuarioRegistroDTO(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}