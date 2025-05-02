package modelo;

public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String contraseña_usuario;
    private String correo_electronico;

    public Usuario(int id_usuario, String nombre_usuario, String contraseña_usuario, String correo_electronico) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contraseña_usuario = contraseña_usuario;
        this.correo_electronico = correo_electronico;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public String getContraseña_usuario(){
        return contraseña_usuario;
    }
    public void setContraseña_usuario(String contraseñaUsuario){
        this.contraseña_usuario = contraseña_usuario;
    }
    public String getCorreo_electronico() {
        return correo_electronico;
    }
    public void setCorreo_electronico(String correoElectronico) {
        this.correo_electronico = correoElectronico;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
