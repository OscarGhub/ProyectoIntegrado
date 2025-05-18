package Dao;

import modelo.FormularioCita;
import modelo.Alertas;
import utils.ConnectionManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolicitarCitaDAO {

    public boolean insertarCita(FormularioCita formulario) {
        String sqlInsert = "INSERT INTO CITA (cliente_id, correo_electronico, fecha_cita, donacion, hora_cita, perro_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getInstance().getConnection()) {
            String correo = formulario.getCorreo_electronico();

            // Validar cliente
            int clienteId = obtenerClienteIdPorCorreo(connection, correo);
            if (clienteId == -1) {
                Alertas.mostrarAlertaWarningGeneral("Correo no encontrado", "El correo electrónico no está registrado.");
                return false;
            }

            // Validar fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(formulario.getFecha_cita());
            Date hoy = new Date();
            if (fecha.before(hoy)) {
                Alertas.mostrarAlertaWarningGeneral("Fecha no válida", "La fecha seleccionada no puede ser anterior a hoy.");
                return false;
            }

            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            // Validar existencia del perro
            if (!existePerro(connection, formulario.getPerro().getPerro_id())) {
                Alertas.mostrarAlertaWarningGeneral("Perro no válido", "No se encontró el perro con el ID proporcionado.");
                return false;
            }

            // Validar disponibilidad del perro (hora y límite de 3 citas)
            if (!verificarDisponibilidad(connection, formulario.getPerro().getPerro_id(), fechaSQL, formulario.getHora_cita())) {
                return false;
            }

            // Insertar cita
            try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
                ps.setInt(1, clienteId);
                ps.setString(2, correo);
                ps.setDate(3, fechaSQL);

                if (formulario.getDonacion() == null || formulario.getDonacion().isBlank()) {
                    ps.setNull(4, Types.DOUBLE);
                } else {
                    ps.setDouble(4, Double.parseDouble(formulario.getDonacion()));
                }

                ps.setString(5, formulario.getHora_cita());
                ps.setInt(6, formulario.getPerro().getPerro_id());

                ps.executeUpdate();

                // Crear notificación para la protectora
                int protectoraId = obtenerProtectoraIdPorPerro(connection, formulario.getPerro().getPerro_id());
                if (protectoraId != -1) {
                    String mensaje = " El cliente " + correo + " ha solicitado una nueva cita con el perro ID "  + formulario.getPerro().getPerro_id() + ".";
                    insertarNotificacionProtectora(connection, protectoraId, mensaje);
                }

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError("Error al registrar cita", "Ocurrió un problema guardando la cita.", e.getMessage());
            return false;
        }
    }

    private int obtenerProtectoraIdPorPerro(Connection connection, int perroId) throws SQLException {
        String sql = "SELECT protectora_id FROM PERRO WHERE perro_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, perroId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("protectora_id");
            }
            return -1;
        }
    }

    private void insertarNotificacionProtectora(Connection connection, int protectoraId, String mensaje) throws SQLException {
        String sql = "INSERT INTO notificaciones_protectora (nueva_cita, informacion, protectora_id, fecha_alta) VALUES (?, ?, ?, SYSDATE)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Sí");
            stmt.setString(2, mensaje);
            stmt.setInt(3, protectoraId);
            stmt.executeUpdate();
        }
    }


    private int obtenerClienteIdPorCorreo(Connection connection, String correo) throws SQLException {
        String sql = "SELECT cliente_id FROM CLIENTE WHERE correo_electronico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cliente_id");
            }
            return -1;
        }
    }

    private boolean existePerro(Connection connection, int perroId) throws SQLException {
        String sql = "SELECT 1 FROM PERRO WHERE perro_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, perroId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    private boolean verificarDisponibilidad(Connection connection, int perroId, java.sql.Date fecha, String hora) throws SQLException {
        String sql = "SELECT COUNT(*) AS total, " +
                "SUM(CASE WHEN hora_cita = ? THEN 1 ELSE 0 END) AS enEsaHora " +
                "FROM cita WHERE perro_id = ? AND fecha_cita = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hora);
            stmt.setInt(2, perroId);
            stmt.setDate(3, fecha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                int enEsaHora = rs.getInt("enEsaHora");

                if (enEsaHora > 0) {
                    Alertas.mostrarAlertaError("Conflicto de horario", "Este perro ya tiene una cita a esa hora.", "Elige otra hora.");
                    return false;
                }

                if (total >= 3) {
                    Alertas.mostrarAlertaError("Cupo lleno", "Este perro ya tiene 3 citas ese día.", "Selecciona otra fecha.");
                    return false;
                }
            }
        }
        return true;
    }
}
