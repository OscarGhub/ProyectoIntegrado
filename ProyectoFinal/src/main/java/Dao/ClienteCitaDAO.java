package Dao;

import modelo.CitasInfo;
import utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteCitaDAO {

    // Método para actualizar la cita
    public boolean actualizarCita(CitasInfo cita, java.time.LocalDate fechaCitaOriginal, String horaCitaOriginal) throws Exception {
        String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, estado = ? " +
                "WHERE cliente_id = (SELECT cliente_id FROM cliente WHERE nombre = ?) " +
                "AND perro_id = (SELECT perro_id FROM perro WHERE nombre = ?) " +
                "AND fecha_cita = ? AND hora_cita = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(cita.getFechaCita()));
            pstmt.setString(2, cita.getHoraCita());
            pstmt.setString(3, "Pendiente");
            pstmt.setString(4, cita.getNombreCliente());
            pstmt.setString(5, cita.getNombrePerro());
            pstmt.setDate(6, Date.valueOf(fechaCitaOriginal));
            pstmt.setString(7, horaCitaOriginal);

            return pstmt.executeUpdate() > 0;
        }
    }

    // Método para cargar las citas desde la base de datos
    public List<CitasInfo> cargarCitas() throws SQLException {
        List<CitasInfo> citas = new ArrayList<>();
        String sql = "SELECT c.donacion, c.estado, c.fecha_cita, c.hora_cita, " +
                "cli.nombre, cli.apellido1, cli.apellido2, " +
                "u.correo_electronico AS correo_usuario, p.nombre AS nombre_perro " +
                "FROM cita c " +
                "JOIN cliente cli ON c.cliente_id = cli.cliente_id " +
                "LEFT JOIN usuario_cliente u ON cli.cliente_id = u.cliente_id " +
                "LEFT JOIN solicitud_adopcion sa ON sa.cliente_id = cli.cliente_id " +
                "LEFT JOIN perro p ON c.perro_id = p.perro_id";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                CitasInfo cita = new CitasInfo();

                double donacion = rs.getDouble("donacion");
                cita.setDonacion(donacion);
                cita.setEstado(rs.getString("estado"));
                cita.setFechaCita(rs.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(rs.getString("hora_cita"));
                cita.setNombreCliente(rs.getString("nombre"));
                cita.setApellido1(rs.getString("apellido1"));
                cita.setApellido2(rs.getString("apellido2"));
                cita.setCorreoUsuario(rs.getString("correo_usuario"));
                cita.setNombrePerro(rs.getString("nombre_perro"));

                citas.add(cita);
            }
        }

        return citas;
    }
}
