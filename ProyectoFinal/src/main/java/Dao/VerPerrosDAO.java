package Dao;

import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class VerPerrosDAO {

    public static class PerroDTO {
        private String nombre;
        private Date fechaNacimiento;
        private String raza;

        public PerroDTO(String nombre, Date fechaNacimiento, String raza) {
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.raza = raza;
        }

        public String getNombre() {
            return nombre;
        }

        public Date getFechaNacimiento() {
            return fechaNacimiento;
        }

        public String getRaza() {
            return raza;
        }
    }


    // En VerPerrosDAO.java
    public boolean tieneNotificaciones(int clienteId) {
        String sql = "SELECT COUNT(*) FROM notificaciones WHERE cliente_id = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<PerroDTO> obtenerPerros() {
        List<PerroDTO> listaPerros = new ArrayList<>();

        String sql = "SELECT p.nombre AS nombre_perro, p.fecha_nacimiento, r.nombre AS nombre_raza " +
                "FROM perro p JOIN raza r ON p.raza = r.id_raza";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre_perro");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String raza = rs.getString("nombre_raza");

                listaPerros.add(new PerroDTO(nombre, fechaNacimiento, raza));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPerros;
    }
}
