package Dao;

import modelo.CitasInfo;
import utils.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerCitasClienteControllerDAO {

    /**
     * Cargar todas las citas de la base de datos.
     *
     * @return List<CitasInfo> lista de citas.
     */
    public List<CitasInfo> cargarCitas() {
        List<CitasInfo> listaCitas = new ArrayList<>();

        String query = "SELECT c.donacion, c.estado, c.fecha_cita, c.hora_cita, " +
                "cli.nombre, cli.apellido1, cli.apellido2, " +
                "u.correo_electronico AS correo_usuario, p.nombre AS nombre_perro " +
                "FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id " +
                "LEFT JOIN perro p ON c.perro_id = p.perro_id";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
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
            Logger.getLogger(VerCitasClienteControllerDAO.class.getName()).log(Level.SEVERE, "Error al cargar las citas", e);
        }

        return listaCitas;
    }

    /**
     * Actualiza una cita en la base de datos.
     *
     * @param cita CitasInfo con los nuevos datos de la cita.
     */
    public void actualizarCita(CitasInfo cita) {
        String query = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, estado = ? " +
                "WHERE fecha_cita = ? AND hora_cita = ? AND cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?) " +
                "AND perro_id = (SELECT perro_id FROM perro WHERE nombre = ?)";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(cita.getFechaCita()));
            pstmt.setString(2, cita.getHoraCita());
            pstmt.setString(3, cita.getEstado());

            // Condiciones para identificar la cita: fecha_cita, hora_cita, nombre_cliente y nombre_perro
            pstmt.setDate(4, Date.valueOf(cita.getFechaCita()));
            pstmt.setString(5, cita.getHoraCita());
            pstmt.setString(6, cita.getNombreCliente());
            pstmt.setString(7, cita.getNombrePerro());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(VerCitasClienteControllerDAO.class.getName()).log(Level.SEVERE, "Error al actualizar la cita", e);
        }
    }
}
