package Dao;

import modelo.Perro;
import modelo.Sexo;
import modelo.UsuarioSesion;
import utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModificarPerrosDao {

    public List<Perro> obtenerTodosLosPerros() {
        List<Perro> lista = new ArrayList<>();
        String sql = "SELECT perro_id, nombre, sexo, fecha_nacimiento FROM perro";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("perro_id");
                String nombre = rs.getString("nombre");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();

                lista.add(new Perro(id, nombre, sexo, fechaNacimiento));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean eliminarPerroPorId(int id) {
        String selectSql = "SELECT cliente_id, nombre FROM perro WHERE perro_id = ?";
        String deleteSql = "DELETE FROM perro WHERE perro_id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement selStmt = conn.prepareStatement(selectSql);
             PreparedStatement delStmt = conn.prepareStatement(deleteSql)) {

            selStmt.setInt(1, id);
            ResultSet rs = selStmt.executeQuery();

            if (rs.next()) {

                String nombrePerro = rs.getString("nombre");


                    String correoProtectora = UsuarioSesion.getCorreoElectronico();
                    String mensaje = "La protectora con correo " + correoProtectora +
                            " ha eliminado al perro adoptado: " + nombrePerro + ".";
                    NotificacionesClienteDao.insertarNotificacion( mensaje, "Si");

            }

            delStmt.setInt(1, id);
            int filas = delStmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarCita(int citaId) {
        String selectSql = "SELECT cliente_id FROM cita WHERE cita_id = ?";
        String deleteSql = "DELETE FROM cita WHERE cita_id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement selStmt = conn.prepareStatement(selectSql);
             PreparedStatement delStmt = conn.prepareStatement(deleteSql)) {

            selStmt.setInt(1, citaId);
            ResultSet rs = selStmt.executeQuery();

            if (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                String correoProtectora = UsuarioSesion.getCorreoElectronico();
                String mensaje = "Tu cita con la protectora " + correoProtectora + " ha sido cancelada.";
                NotificacionesClienteDao.insertarNotificacion( mensaje, "Si");
            }

            delStmt.setInt(1, citaId);
            int filas = delStmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }






}
