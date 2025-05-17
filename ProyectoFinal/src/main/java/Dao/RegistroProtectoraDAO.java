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

            // Validar correo electrónico existente
            String checkCorreo = "SELECT COUNT(*) FROM protectora WHERE correo_electronico = ?";
            try (PreparedStatement stmtCheckCorreo = conn.prepareStatement(checkCorreo)) {
                stmtCheckCorreo.setString(1, protectora.getCorreoElectronico());
                try (ResultSet rs = stmtCheckCorreo.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        Alertas.mostrarAlertaError(null, "Error", "El correo ya está registrado.");
                        return false;
                    }
                }
            }

            // Validar nombre de usuario existente
            String checkUsuario = "SELECT COUNT(*) FROM protectora WHERE nombre = ?";
            try (PreparedStatement stmtCheckUsuario = conn.prepareStatement(checkUsuario)) {
                stmtCheckUsuario.setString(1, protectora.getNombreUsuario());
                try (ResultSet rs = stmtCheckUsuario.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        Alertas.mostrarAlertaError(null, "Error", "El nombre de usuario ya está registrado.");
                        return false;
                    }
                }
            }

            // Obtener próximo valor de la secuencia para el ID
            int protectoraId;
            String sqlSeq = "SELECT protectora_seq.NEXTVAL FROM dual"; // Ajusta el nombre de la secuencia
            try (PreparedStatement stmtSeq = conn.prepareStatement(sqlSeq);
                 ResultSet rsSeq = stmtSeq.executeQuery()) {
                if (rsSeq.next()) {
                    protectoraId = rsSeq.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la secuencia.");
                }
            }

            // Insertar en tabla protectora con ID explícito
            String insertProtectora = """
                INSERT INTO protectora (id_protectora, telefono, correo_electronico, codigo_postal, localidad, provincia, pais, tipo_via, nombre_via, fecha_alta)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)
            """;

            try (PreparedStatement stmt = conn.prepareStatement(insertProtectora)) {
                stmt.setInt(1, protectoraId);
                stmt.setString(2, protectora.getTelefono());
                stmt.setString(3, protectora.getCorreoElectronico());
                stmt.setString(4, protectora.getCodigoPostal());
                stmt.setString(5, protectora.getLocalidad());
                stmt.setString(6, protectora.getProvincia());
                stmt.setString(7, protectora.getPais());
                stmt.setString(8, protectora.getTipoVia());
                stmt.setString(9, protectora.getNombreVia());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("No se pudo insertar la protectora, no se afectaron filas.");
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

                int affectedRows = stmtUsuario.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("No se pudo insertar el usuario de la protectora.");
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError(null, "Error", "No se pudo registrar a la protectora: " + e.getMessage());
            return false;
        }
    }
}
