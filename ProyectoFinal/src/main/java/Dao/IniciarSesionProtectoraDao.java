package Dao;

import modelo.Alertas;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IniciarSesionProtectoraDao {

    public static String obtenerHashContraseniaP(String nombreUsuario) {
        String sql = "SELECT contrasena FROM usuario_protectora WHERE nombre_usuario = ?";

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
