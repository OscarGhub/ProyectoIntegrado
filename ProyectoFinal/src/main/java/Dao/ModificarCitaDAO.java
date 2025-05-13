package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModificarCitaDAO {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "C##PROYECTOINTEGRADO";
    private static final String PASSWORD = "123456";

    public static boolean actualizarCita(int citaId, String nuevaFecha, String nuevaHora, String nuevoEstado) {
        String query = "UPDATE cita SET fecha_cita = TO_DATE(?, 'YYYY-MM-DD'), hora_cita = ?, estado = ? WHERE cita_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nuevaFecha);
            pstmt.setString(2, nuevaHora);
            pstmt.setString(3, nuevoEstado);
            pstmt.setInt(4, citaId); // Asumiendo que cada cita tiene un ID único

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Retorna true si la cita se actualizó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

