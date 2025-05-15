package Dao;

import utils.ConnectionManager;
import java.sql.*;

public class IniciarSesionClienteDao {
    public static String obtenerHashContrasenia(String nombreUsuario) {
        String sql = "SELECT contrasena FROM usuario_cliente WHERE nombre_usuario = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return rs.getString("contrasena");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}