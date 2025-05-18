package Dao;

import utils.ConnectionManager;
import modelo.Alertas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioAdopcionDAO {

    public static int obtenerIdPerro(String nombrePerro) throws SQLException {
        String sql = "SELECT perro_id FROM perro WHERE nombre = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombrePerro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("perro_id");
                }
            }
        }
        return -1;
    }

    public static int obtenerIdCliente(String correo) throws SQLException {
        String sql = "SELECT cliente_id FROM cliente WHERE CORREO_ELECTRONICO = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cliente_id");
                }
            }
        }
        return -1;
    }

    public static String[] obtenerInfoPerro(String nombrePerro) throws SQLException {
        String sql = "SELECT raza, sexo FROM perro WHERE nombre = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombrePerro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String raza = rs.getString("raza");
                    String sexo = rs.getString("sexo");
                    return new String[] { raza, sexo };
                }
            }
        }
        return null;
    }

    public static String[] obtenerInfoCliente(String correo) throws SQLException {
        String sql = "SELECT nombre, apellido1, apellido2 FROM cliente WHERE CORREO_ELECTRONICO = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido1 = rs.getString("apellido1");
                    String apellido2 = rs.getString("apellido2");
                    return new String[] { nombre, apellido1, apellido2 };
                }
            }
        }
        return null;
    }

    public static boolean insertarSolicitudAdopcion(int perroId, int clienteId, double donacion) {
        String sqlInsert = "INSERT INTO solicitud_adopcion (perro_id, cliente_id, donacion) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlInsert)) {

            ps.setInt(1, perroId);
            ps.setInt(2, clienteId);
            ps.setDouble(3, donacion);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                // Insertar notificación para la protectora relacionada al perro
                int protectoraId = obtenerProtectoraIdPorPerro(con, perroId);
                if (protectoraId != -1) {
                    String mensaje = "El cliente con ID " + clienteId + " ha enviado una solicitud de adopción para el perro  " + perroId + ".";
                    insertarNotificacionProtectora(con, protectoraId, mensaje);
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError("Error al registrar adopción", "Ocurrió un problema guardando la solicitud.", e.getMessage());
        }

        return false;
    }

    private static int obtenerProtectoraIdPorPerro(Connection con, int perroId) throws SQLException {
        String sql = "SELECT protectora_id FROM perro WHERE perro_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, perroId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("protectora_id");
                }
            }
        }
        return -1;
    }

    private static void insertarNotificacionProtectora(Connection con, int protectoraId, String mensaje) throws SQLException {
        String sql = "INSERT INTO notificaciones_protectora (nueva_cita, informacion, protectora_id, fecha_alta) VALUES (?, ?, ?, SYSDATE)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "No"); // porque es adopción, no cita
            stmt.setString(2, mensaje);
            stmt.setInt(3, protectoraId);
            stmt.executeUpdate();
        }
    }
}
