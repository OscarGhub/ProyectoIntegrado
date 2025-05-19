package Dao;

import modelo.Alertas;
import org.mindrot.jbcrypt.BCrypt;
import utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjustesDao {

    public static boolean cambioUsuarioAjustes(String usuario, String nuevoUsuario) {
        boolean resultado = true;
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {


            String verificarUsuario = "SELECT nombre_usuario FROM usuario_cliente WHERE nombre_usuario = ?";
            try (PreparedStatement ps = connection.prepareStatement(verificarUsuario)) {
                ps.setString(1, usuario);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    Alertas.mostrarAlertaError(null, "Error", "No se ha encontrado el usuario.");
                    resultado = false;
                }
            }


            String cambiarUsuario = "UPDATE usuario_cliente SET nombre_usuario = ? WHERE nombre_usuario = ?";
            try (PreparedStatement ps2 = connection.prepareStatement(cambiarUsuario)) {
                ps2.setString(1, nuevoUsuario);
                ps2.setString(2, usuario);

                int filasAfectadas = ps2.executeUpdate();
                if (filasAfectadas == 0) {
                    Alertas.mostrarAlertaError(null, "Error", "No se pudo actualizar el nombre de usuario.");
                    resultado = false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError(null, "Error", "Algo ha salido mal.");
            resultado = false;
        }

        return resultado;
    }

    public static boolean cambioCorreoAjustes(String correo, String nuevoCorreo) {
        boolean resultado = true;
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {


            String verificarCorreo = "SELECT correo_electronico FROM usuario_cliente WHERE correo_electronico = ?";
            try (PreparedStatement ps = connection.prepareStatement(verificarCorreo)) {
                ps.setString(1, correo);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    Alertas.mostrarAlertaError(null, "Error", "No se ha encontrado el correo electrónico.");
                    resultado = false;
                }
            }


            String cambiarCorreo = "UPDATE usuario_cliente SET correo_electronico = ? WHERE correo_electronico = ?";
            try (PreparedStatement ps2 = connection.prepareStatement(cambiarCorreo)) {
                ps2.setString(1, nuevoCorreo);
                ps2.setString(2, correo);

                int filasAfectadas = ps2.executeUpdate();
                if (filasAfectadas == 0) {
                    Alertas.mostrarAlertaError(null, "Error", "No se pudo actualizar el correo electrónico.");
                    resultado = false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError(null, "Error", "Algo ha salido mal.");
            resultado = false;
        }

        return resultado;
    }


    public static boolean cambioContraseniaAjustes(String contrasena, String nuevaContrasena) {
        boolean resultado = true;
        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            // Verificar si la contraseña actual ingresada es correcta
            String verificarContrasena = "SELECT contrasena FROM usuario_cliente WHERE correo_electronico = ?"; // Usamos correo o id para obtener la contraseña
            try (PreparedStatement ps = connection.prepareStatement(verificarContrasena)) {
                // Obtén el correo como identificador único
                String correo = modelo.UsuarioSesion.getUsuario().getCorreoElectronico();
                ps.setString(1, correo);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String contrasenaEncriptada = rs.getString("contrasena");

                    // Verificar si la contraseña ingresada coincide con la contraseña almacenada
                    if (!BCrypt.checkpw(contrasena, contrasenaEncriptada)) {
                        Alertas.mostrarAlertaError(null, "Error", "La contraseña actual no es correcta.");
                        resultado = false;
                    } else if (BCrypt.checkpw(nuevaContrasena, contrasenaEncriptada)) {
                        // Si la nueva contraseña es la misma que la actual, mostrar advertencia
                        Alertas.mostrarAlertaError(null, "Advertencia", "La nueva contraseña no puede ser la misma que la actual.");
                        resultado = false;
                    }
                } else {
                    Alertas.mostrarAlertaError(null, "Error", "No se encontró el usuario.");
                    resultado = false;
                }
            }

            // Si la contraseña actual es correcta y la nueva contraseña no es la misma, proceder con el cambio
            if (resultado) {
                String nuevaContrasenaEncriptada = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());

                // Actualizar la contraseña en la base de datos
                String cambiarContrasena = "UPDATE usuario_cliente SET contrasena = ? WHERE correo_electronico = ?";
                try (PreparedStatement ps2 = connection.prepareStatement(cambiarContrasena)) {
                    ps2.setString(1, nuevaContrasenaEncriptada);  // Establecemos la nueva contraseña encriptada
                    String correo = modelo.UsuarioSesion.getUsuario().getCorreoElectronico();
                    ps2.setString(2, correo);  // Establecemos el correo del usuario actual

                    int filasAfectadas = ps2.executeUpdate();
                    if (filasAfectadas == 0) {
                        Alertas.mostrarAlertaError(null, "Error", "No se pudo actualizar la contraseña.");
                        resultado = false;
                    } else {
                        Alertas.mostrarAlertaConfirmacion(null, "Éxito", "Contraseña actualizada correctamente.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError(null, "Error", "Algo ha salido mal al cambiar la contraseña.");
            resultado = false;
        }

        return resultado;
    }


}
