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
        private String patologias;

        public PerroDTO(String nombre, Date fechaNacimiento, String raza, String patologias) {
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.raza = raza;
            this.patologias = patologias;
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

        public String getPatologias() {
            return patologias;
        }

        public void setPatologias(String patologias) {
            this.patologias = patologias;
        }
    }

    public List<PerroDTO> obtenerPerros() {
        List<PerroDTO> listaPerros = new ArrayList<>();

        String sql = "SELECT " +
                "    p.nombre AS nombre_perro, " +
                "    p.fecha_nacimiento, " +
                "    r.nombre AS nombre_raza, " +
                "    LISTAGG(pa.nombre, ', ') WITHIN GROUP (ORDER BY pa.nombre) AS patologias " +
                "FROM " +
                "    perro p " +
                "JOIN " +
                "    raza r ON p.raza = r.id_raza " +
                "LEFT JOIN " +
                "    perro_patologia pp ON p.perro_id = pp.perro_id " +
                "LEFT JOIN " +
                "    patologia pa ON pp.id_patologia = pa.id_patologia " +
                "GROUP BY " +
                "    p.perro_id, p.nombre, p.fecha_nacimiento, r.nombre";


        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre_perro");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String raza = rs.getString("nombre_raza");
                String patologias = rs.getString("patologias");

                if (patologias == null) {
                    patologias = "No disponible";
                }

                listaPerros.add(new PerroDTO(nombre, fechaNacimiento, raza, patologias));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPerros;
    }

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
}
