package modelo;

public class UsuarioSesion {

    private static Usuario usuarioActual;
    private static Protectora protectoraActual;

    private UsuarioSesion() {

    }

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
        protectoraActual = null;
    }

    public static void iniciarSesion(Protectora protectora) {
        protectoraActual = protectora;
        usuarioActual = null;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        protectoraActual = null;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static Protectora getProtectora() {
        return protectoraActual;
    }

    public static String getCorreoElectronico() {
        if (usuarioActual != null) {
            return usuarioActual.getCorreoElectronico();
        } else if (protectoraActual != null) {
            return protectoraActual.getCorreoElectronico();
        } else {
            return null;
        }
    }

    public static boolean estaLogueado() {
        return usuarioActual != null || protectoraActual != null;
    }
}
