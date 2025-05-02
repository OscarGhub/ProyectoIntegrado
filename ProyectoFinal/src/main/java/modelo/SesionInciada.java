package modelo;

public class SesionInciada {

    private static boolean sesionIniciada = false;
    private static Usuario usuarioActual;

    public static boolean iniciarSesion(Usuario usuario) {
        boolean esIniciado = false;
        if (sesionIniciada==true) {
            esIniciado = true;
        } else {
            usuarioActual = usuario;
            sesionIniciada = true;
        }

        return esIniciado;
    }

    public static boolean iniciarSesion() {
        return sesionIniciada;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
