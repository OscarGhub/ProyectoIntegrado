package Dao;

import modelo.Alertas;
import modelo.Usuario;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IniciarSesionProtectoraDao {

    public static String obtenerHashContraseniaP(String nombreUsuario) {
        String sql = "SELECT contrasena FROM usuario_protectora WHERE nombre_usuario = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();

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

    public static Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        Usuario usuario = null;
        String sql = "SELECT ID_USUARIO_PROTECTORA, NOMBRE_USUARIO, CONTRASENA, CORREO_ELECTRONICO " +
                "FROM USUARIO_PROTECTORA WHERE NOMBRE_USUARIO = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setContrasena(rs.getString("CONTRASENA"));
                usuario.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
                usuario.setIdUsuario(rs.getInt("ID_USUARIO_PROTECTORA"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }



}
