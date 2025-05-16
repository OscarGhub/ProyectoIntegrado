package modelo;

public class UsuarioSesion {
    private static String correoElectronico;
    private static boolean sesionIniciada = false;
    private static Usuario usuarioActual;

    public static String getCorreoElectronico() {
        return correoElectronico;
    }

    public static void setCorreoElectronico(String correo) {
        correoElectronico = correo;
    }

    public static boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
        correoElectronico = usuario.getCorreoElectronico();
        sesionIniciada = true;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        correoElectronico = null;
        sesionIniciada = false;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setSesionIniciada(boolean sesionIniciada) {
        UsuarioSesion.sesionIniciada = sesionIniciada;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        UsuarioSesion.usuarioActual = usuarioActual;
    }
}
