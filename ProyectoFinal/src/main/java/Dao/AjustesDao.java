package Dao;

import modelo.Alertas;
import modelo.UsuarioSesion;
import org.mindrot.jbcrypt.BCrypt;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjustesDao {

    public static boolean cambioUsuarioAjustes(String usuarioActual, String nuevoUsuario) {
        boolean resultado = true;
        int idUsuario = UsuarioSesion.getUsuario().getIdUsuario();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            // Verifica si el usuario actual coincide con el ID de sesión
            String verificarUsuario = "SELECT nombre_usuario FROM usuario_cliente WHERE id_usuario_cliente = ? AND nombre_usuario = ?";
            try (PreparedStatement ps = connection.prepareStatement(verificarUsuario)) {
                ps.setInt(1, idUsuario);
                ps.setString(2, usuarioActual);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    Alertas.mostrarAlertaError(null, "Error", "No se ha encontrado el usuario actual.");
                    return false;
                }
            }

            // Actualiza el nombre de usuario
            String cambiarUsuario = "UPDATE usuario_cliente SET nombre_usuario = ? WHERE id_usuario_cliente = ?";
            try (PreparedStatement ps2 = connection.prepareStatement(cambiarUsuario)) {
                ps2.setString(1, nuevoUsuario);
                ps2.setInt(2, idUsuario);

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

    public static boolean cambioCorreoAjustes(String correoActual, String nuevoCorreo) {
        boolean resultado = true;
        int idUsuario = UsuarioSesion.getUsuario().getIdUsuario();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            // Verifica si el correo actual coincide con el ID de sesión
            String verificarCorreo = "SELECT correo_electronico FROM usuario_cliente WHERE id_usuario_cliente = ? AND correo_electronico = ?";
            try (PreparedStatement ps = connection.prepareStatement(verificarCorreo)) {
                ps.setInt(1, idUsuario);
                ps.setString(2, correoActual);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    Alertas.mostrarAlertaError(null, "Error", "No se ha encontrado el correo electrónico actual.");
                    return false;
                }
            }

            // Actualiza el correo
            String cambiarCorreo = "UPDATE usuario_cliente SET correo_electronico = ? WHERE id_usuario_cliente = ?";
            try (PreparedStatement ps2 = connection.prepareStatement(cambiarCorreo)) {
                ps2.setString(1, nuevoCorreo);
                ps2.setInt(2, idUsuario);

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

    public static boolean cambioContraseniaAjustes(String contrasenaActual, String nuevaContrasena) {
        boolean resultado = true;
        int idUsuario = UsuarioSesion.getUsuario().getIdUsuario();

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {

            // Verifica contraseña actual por ID de usuario
            String verificarContrasena = "SELECT contrasena FROM usuario_cliente WHERE id_usuario_cliente = ?";
            try (PreparedStatement ps = connection.prepareStatement(verificarContrasena)) {
                ps.setInt(1, idUsuario);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String contrasenaEncriptada = rs.getString("contrasena");

                    if (!BCrypt.checkpw(contrasenaActual, contrasenaEncriptada)) {
                        Alertas.mostrarAlertaError(null, "Error", "La contraseña actual no es correcta.");
                        return false;
                    }

                    if (BCrypt.checkpw(nuevaContrasena, contrasenaEncriptada)) {
                        Alertas.mostrarAlertaError(null, "Advertencia", "La nueva contraseña no puede ser la misma que la actual.");
                        return false;
                    }

                } else {
                    Alertas.mostrarAlertaError(null, "Error", "No se encontró el usuario.");
                    return false;
                }
            }

            // Actualiza la contraseña
            String nuevaContrasenaEncriptada = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
            String cambiarContrasena = "UPDATE usuario_cliente SET contrasena = ? WHERE id_usuario_cliente = ?";

            try (PreparedStatement ps2 = connection.prepareStatement(cambiarContrasena)) {
                ps2.setString(1, nuevaContrasenaEncriptada);
                ps2.setInt(2, idUsuario);

                int filasAfectadas = ps2.executeUpdate();
                if (filasAfectadas == 0) {
                    Alertas.mostrarAlertaError(null, "Error", "No se pudo actualizar la contraseña.");
                    resultado = false;
                } else {
                    Alertas.mostrarAlertaConfirmacion(null, "Éxito", "Contraseña actualizada correctamente.");
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
