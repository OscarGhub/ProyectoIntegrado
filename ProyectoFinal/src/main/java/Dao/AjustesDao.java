package Dao;

import modelo.Alertas;
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


            String verificarContrasena = "SELECT contrasena FROM usuario_cliente WHERE contrasena = ?";
            try (PreparedStatement ps = connection.prepareStatement(verificarContrasena)) {
                ps.setString(1, contrasena);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    Alertas.mostrarAlertaError(null, "Error", "No se ha encontrado la contraseña.");
                    resultado = false;
                }
            }


            String cambiarContrasena = "UPDATE usuario_cliente SET contrasena = ? WHERE contrasena = ?";
            try (PreparedStatement ps2 = connection.prepareStatement(cambiarContrasena)) {
                ps2.setString(1, nuevaContrasena);
                ps2.setString(2, contrasena);

                int filasAfectadas = ps2.executeUpdate();
                if (filasAfectadas == 0) {
                    Alertas.mostrarAlertaError(null, "Error", "No se pudo actualizar la contraseña.");
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
}
