package modelo;

public class UsuarioSesion {
    private static String correoElectronico;

    public static String getCorreoElectronico() {
        return correoElectronico;
    }

    public static void setCorreoElectronico(String correo) {
        correoElectronico = correo;
    }
}
