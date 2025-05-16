package Dao;

import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioAdopcionDAO {

    public static int obtenerIdPerro(String nombrePerro) throws SQLException {
        String sql = "SELECT id FROM perros WHERE nombre = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombrePerro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }

    public static int obtenerIdCliente(String correo) throws SQLException {
        String sql = "SELECT id FROM clientes WHERE correo = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }

    public static String[] obtenerInfoPerro(String nombrePerro) throws SQLException {
        String sql = "SELECT raza, sexo FROM perros WHERE nombre = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombrePerro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String raza = rs.getString("raza");
                    String sexo = rs.getString("sexo");
                    return new String[] { raza, sexo };
                }
            }
        }
        return null;
    }

    public static String[] obtenerInfoCliente(String correo) throws SQLException {
        String sql = "SELECT nombre, apellido1, apellido2 FROM clientes WHERE correo = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido1 = rs.getString("apellido1");
                    String apellido2 = rs.getString("apellido2");
                    return new String[] { nombre, apellido1, apellido2 };
                }
            }
        }
        return null;
    }

    public static boolean insertarSolicitudAdopcion(int perroId, int clienteId, double donacion) throws SQLException {
        String sql = "INSERT INTO solicitudes_adopcion (id_perro, id_cliente, donacion) VALUES (?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, perroId);
            ps.setInt(2, clienteId);
            ps.setDouble(3, donacion);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
}
