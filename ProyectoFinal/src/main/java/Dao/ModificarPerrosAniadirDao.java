package Dao;


import modelo.UsuarioSesion;
import utils.ConnectionManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModificarPerrosAniadirDao {

    public static List<String> obtenerRazas() {
        List<String> razas = new ArrayList<>();
        String sql = "SELECT nombre FROM raza";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                razas.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return razas;
    }

    public static void insertarPerro(String nombre, LocalDate fechaNacimiento, String nombreRaza, String sexo) {
        String sql = """
        INSERT INTO perro (
            nombre, 
            fecha_nacimiento, 
            raza, 
            sexo, 
            adoptado, 
            protectora_id, 
            fecha_alta, 
            fecha_modificacion
        )
        VALUES (?, ?, (SELECT id_raza FROM raza WHERE nombre = ?), ?, 'No', 1, SYSDATE, SYSDATE)
    """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setDate(2, Date.valueOf(fechaNacimiento));
            stmt.setString(3, nombreRaza);
            stmt.setString(4, sexo);

            String correoProtectora = UsuarioSesion.getCorreoElectronico();
            String mensaje = "La protectora con correo " + correoProtectora + " ha añadido un perro nuevo.";
            NotificacionesClienteDao.insertarNotificacion(mensaje, "Si");

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
