package Dao;

import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificacionesProtectoraDao {

    public static void insertarNotifiacionProtectora(int idProtectora){

        String sql = "INSERT INTO notificaciones_protectora VALUES(?,?)";

    }

    public static boolean verificarNotificacionesProtectora(int idProtectora) throws SQLException {

        boolean resultado = false;

        String notificacionesProtectora = "SELECT * FROM notificaciones_protectora WHERE idNotificacion = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(notificacionesProtectora)) {

            ps.setInt(1, idProtectora);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }
}
