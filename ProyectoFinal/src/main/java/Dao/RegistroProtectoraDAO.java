package Dao;

import modelo.Protectora;
import utils.ConnectionManager;
import modelo.Alertas;

import java.sql.*;

public class RegistroProtectoraDAO {

    public static boolean registrarProtectora(Protectora protectora) {
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {

            // Validar correo electrónico existente
            String checkCorreo = "SELECT COUNT(*) FROM protectora WHERE correo_electronico = ?";
            try (PreparedStatement stmtCheckCorreo = conn.prepareStatement(checkCorreo)) {
                stmtCheckCorreo.setString(1, protectora.getCorreoElectronico());
                ResultSet rs = stmtCheckCorreo.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    Alertas.mostrarAlertaError(null, "Error", "El correo ya está registrado.");
                    return false;
                }
            }

            // Validar nombre de usuario existente
            String checkUsuario = "SELECT COUNT(*) FROM protectora WHERE nombre = ?";
            try (PreparedStatement stmtCheckUsuario = conn.prepareStatement(checkUsuario)) {
                stmtCheckUsuario.setString(1, protectora.getNombreUsuario());
                ResultSet rs = stmtCheckUsuario.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    Alertas.mostrarAlertaError(null, "Error", "El nombre de usuario ya está registrado.");
                    return false;
                }
            }

            // Insertar en tabla protectora
            String insertProtectora = """
                INSERT INTO protectora (telefono, correo_electronico, codigo_postal, localidad, provincia, pais, tipo_via, nombre_via, fecha_alta)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)
            """;

            int protectoraId;
            try (PreparedStatement stmt = conn.prepareStatement(insertProtectora, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, protectora.getTelefono());
                stmt.setString(2, protectora.getCorreoElectronico());
                stmt.setString(3, protectora.getCodigoPostal());
                stmt.setString(4, protectora.getLocalidad());
                stmt.setString(5, protectora.getProvincia());
                stmt.setString(6, protectora.getPais());
                stmt.setString(7, protectora.getTipoVia());
                stmt.setString(8, protectora.getNombreVia());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    protectoraId = rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la protectora.");
                }
            }

            // Insertar en tabla usuario_protectora
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
            Alertas.mostrarAlertaError(null, "Error", "No se pudo registrar a la protectora.");
            return false;
        }
    }
}
