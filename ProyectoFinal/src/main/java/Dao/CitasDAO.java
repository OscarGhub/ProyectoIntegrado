package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.CitasInfo;
import utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitasDAO {

    public static ObservableList<CitasInfo> obtenerCitas() {
        ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();

        String query = "SELECT c.donacion, c.estado, c.fecha_cita, c.hora_cita, " +
                "cli.nombre, cli.apellido1, cli.apellido2, " +
                "u.correo_electronico AS correo_usuario, p.nombre AS nombre_perro " +
                "FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id " +
                "LEFT JOIN perro p ON c.perro_id = p.perro_id";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CitasInfo cita = new CitasInfo(
                        rs.getDouble("donacion"),
                        rs.getString("estado"),
                        rs.getDate("fecha_cita").toLocalDate(),
                        rs.getString("hora_cita"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("correo_usuario"),
                        rs.getString("nombre_perro")
                );
                listaCitas.add(cita);
            }

        } catch (SQLException e) {
            Logger.getLogger(CitasDAO.class.getName()).log(Level.SEVERE, "Error al obtener citas", e);
        }

        return listaCitas;
    }

    public boolean cancelarCita(CitasInfo cita) {
        String updateQuery = "UPDATE cita SET estado = ? " +
                "WHERE fecha_cita = ? AND hora_cita = ? AND cliente_id = (" +
                "SELECT cliente_id FROM cliente WHERE nombre = ? AND apellido1 = ? AND apellido2 = ?)";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, "Cancelada");
            pstmt.setDate(2, Date.valueOf(cita.getFechaCita()));
            pstmt.setString(3, cita.getHoraCita());
            pstmt.setString(4, cita.getNombreCliente());
            pstmt.setString(5, cita.getApellido1());
            pstmt.setString(6, cita.getApellido2());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un logger
            return false;
        }
    }

    public ObservableList<CitasInfo> obtenerCitasPorCorreo(String correo) {
        ObservableList<CitasInfo> listaCitas = FXCollections.observableArrayList();

        String query = "SELECT c.donacion, c.estado, c.fecha_cita, c.hora_cita, " +
                "cli.nombre, cli.apellido1, cli.apellido2, " +
                "u.correo_electronico AS correo_usuario, p.nombre AS nombre_perro " +
                "FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id " +
                "LEFT JOIN perro p ON c.perro_id = p.perro_id " +
                "WHERE u.correo_electronico = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, correo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CitasInfo cita = new CitasInfo(
                            rs.getDouble("donacion"),
                            rs.getString("estado"),
                            rs.getDate("fecha_cita").toLocalDate(),
                            rs.getString("hora_cita"),
                            rs.getString("nombre"),
                            rs.getString("apellido1"),
                            rs.getString("apellido2"),
                            rs.getString("correo_usuario"),
                            rs.getString("nombre_perro")
                    );
                    listaCitas.add(cita);
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(CitasDAO.class.getName()).log(Level.SEVERE, "Error al obtener citas por correo", e);
        }

        return listaCitas;
    }

    public static boolean existeCitaConPerro(String correoUsuario, int idPerro) {
        String query = "SELECT COUNT(*) FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "WHERE u.correo_electronico = ? AND c.perro_id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, correoUsuario);
            pstmt.setInt(2, idPerro);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(CitasDAO.class.getName()).log(Level.SEVERE, "Error al verificar la cita", e);
        }

        return false;
    }

}
