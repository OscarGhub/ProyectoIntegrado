package Dao;

import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificacionesClienteDao {


    public static void insertarNotifiacionCliente(int idCliente){

        String sql = "INSERT INTO notificaciones_protectora VALUES(?,?)";

    }

    public static boolean verificarNotificacionesCliente(int idCliente) throws SQLException {

        boolean resultado = false;

        String notificacionesProtectora = "SELECT * FROM notificaciones_cliente WHERE idNotificacion = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(notificacionesProtectora)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }
}
