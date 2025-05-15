package modelo;

import org.mindrot.jbcrypt.BCrypt;

public class EncriptarContrasenia {
    private static final int COSTE_HASH = 12;

    public static String encriptar(String contraseniaPlana) {
        return BCrypt.hashpw(contraseniaPlana, BCrypt.gensalt(COSTE_HASH));
    }

    public static boolean verificar(String contraseniaPlana, String contraseniaEncriptada) {
        return BCrypt.checkpw(contraseniaPlana, contraseniaEncriptada);
    }
}