package Dao;

import modelo.Perro;
import modelo.Sexo;
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
        String sql = "DELETE FROM perro WHERE perro_id = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
