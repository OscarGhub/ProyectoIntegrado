package Dao;

import modelo.Protectora;
import utils.ConnectionManager;
import modelo.Alertas;

import java.sql.*;

public class RegistroProtectoraDAO {

    public static boolean registrarProtectora(Protectora protectora) {

        if (protectora.getCorreoElectronico() == null || protectora.getCorreoElectronico().isEmpty()) {
            Alertas.mostrarAlertaError(null, "Error", "Correo electrónico no puede estar vacío.");
            return false;
        }
        if (protectora.getContrasena() == null || protectora.getContrasena().isEmpty()) {
            Alertas.mostrarAlertaError(null, "Error", "Contraseña no puede estar vacía.");
            return false;
        }
        if (protectora.getNombreUsuario() == null || protectora.getNombreUsuario().isEmpty()) {
            Alertas.mostrarAlertaError(null, "Error", "Nombre de usuario no puede estar vacío.");
            return false;
        }

        try (Connection conn = ConnectionManager.getInstance().getConnection()) {

            // Validar correo y nombre de usuario únicos
            String checkQuery = """
                SELECT COUNT(*) FROM usuario_protectora 
                WHERE correo_electronico = ? OR nombre_usuario = ?
            """;
            try (PreparedStatement stmtCheck = conn.prepareStatement(checkQuery)) {
                stmtCheck.setString(1, protectora.getCorreoElectronico());
                stmtCheck.setString(2, protectora.getNombreUsuario());
                try (ResultSet rs = stmtCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        Alertas.mostrarAlertaError(null, "Error", "El correo o nombre de usuario ya está registrado.");
                        return false;
                    }
                }
            }

            // Insertar en protectora
            String insertProtectora = """
                INSERT INTO protectora (nombre, telefono, correo_electronico, codigo_postal, localidad, provincia, pais, tipo_via, nombre_via, fecha_alta)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)
            """;

            int protectoraId;
            try (PreparedStatement stmt = conn.prepareStatement(insertProtectora, new String[]{"protectora_id"})) {
                stmt.setString(1, protectora.getNombreUsuario()); // Asumiendo que el "nombre" es igual al usuario
                stmt.setString(2, protectora.getTelefono());
                stmt.setString(3, protectora.getCorreoElectronico());
                stmt.setString(4, protectora.getCodigoPostal());
                stmt.setString(5, protectora.getLocalidad());
                stmt.setString(6, protectora.getProvincia());
                stmt.setString(7, protectora.getPais());
                stmt.setString(8, protectora.getTipoVia());
                stmt.setString(9, protectora.getNombreVia());

                stmt.executeUpdate();

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        protectoraId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la protectora.");
                    }
                }
            }

            // Insertar en usuario_protectora
            String insertUsuario = """
                INSERT INTO usuario_protectora (nombre_usuario, contrasena, correo_electronico, id_protectora, fecha_alta)
                VALUES (?, ?, ?, ?, SYSDATE)
            """;

            try (PreparedStatement stmtUsuario = conn.prepareStatement(insertUsuario)) {
                stmtUsuario.setString(1, protectora.getNombreUsuario());
                stmtUsuario.setString(2, protectora.getContrasena());
                stmtUsuario.setString(3, protectora.getCorreoElectronico());
                stmtUsuario.setInt(4, protectoraId);

                stmtUsuario.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError(null, "Error", "No se pudo registrar a la protectora: " + e.getMessage());
            return false;
        }
    }
}
