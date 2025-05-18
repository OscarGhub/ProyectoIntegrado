package Dao;

import modelo.Usuario;
import utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IniciarSesionClienteDao {

    public static String obtenerHashContrasenia(String nombreUsuario) {
        String sql = "SELECT contrasena FROM usuario_cliente WHERE nombre_usuario = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("contrasena");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        Usuario usuario = null;
        String sql = "SELECT ID_USUARIO_CLIENTE, NOMBRE_USUARIO, CONTRASENA, CORREO_ELECTRONICO FROM USUARIO_CLIENTE WHERE NOMBRE_USUARIO = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("ID_USUARIO_CLIENTE"));
                usuario.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
