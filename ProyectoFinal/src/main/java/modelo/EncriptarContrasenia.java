package modelo;


import org.mindrot.jbcrypt.BCrypt;
public class EncriptarContrasenia {

    public static String encriptarContraseia(String perfil, String contrasenia) {

     String contraseniaEncriptada = "";
     if(perfil.toLowerCase().equals("cliente")){

         contraseniaEncriptada = BCrypt.hashpw(contrasenia, BCrypt.gensalt());

     } else if (perfil.toLowerCase().equals("protectora")) {

         contraseniaEncriptada = BCrypt.hashpw(contrasenia, BCrypt.gensalt());

     }
        return contraseniaEncriptada;
    }


    public static boolean validarContrasenia(String textoPlano, String hashGuardado) {
        boolean contraseniaValidada = false;

        if (BCrypt.checkpw(textoPlano, hashGuardado)){
            contraseniaValidada = true;
        }

        return contraseniaValidada;
    }
}
