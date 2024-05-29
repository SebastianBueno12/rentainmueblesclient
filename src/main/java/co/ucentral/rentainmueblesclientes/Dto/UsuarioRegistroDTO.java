package co.ucentral.rentainmueblesclientes.Dto;

public class UsuarioRegistroDTO {

    private String nombre;
    private String correo;
    private String clave;
    private String direccion;
    private String telefono;

    public UsuarioRegistroDTO() {}

    public UsuarioRegistroDTO(String nombre, String correo, String clave, String direccion, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave= clave;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y setters
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}