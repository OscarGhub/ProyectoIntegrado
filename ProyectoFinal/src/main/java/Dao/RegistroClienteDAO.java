package Dao;

import modelo.Alertas;
import utils.ConnectionManager;
import java.sql.*;
import modelo.Usuario;

public class RegistroClienteDAO {

    public static boolean registrarClienteYUsuario(Usuario usuario) {
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {

            // Verificar si ya existe ese usuario o correo
            String checkQuery = "SELECT COUNT(*) FROM usuario_cliente WHERE correo_electronico = ? OR nombre_usuario = ?";
            try (PreparedStatement stmtCheck = conn.prepareStatement(checkQuery)) {
                stmtCheck.setString(1, usuario.getCorreoElectronico());
                stmtCheck.setString(2, usuario.getNombreUsuario());
                ResultSet rs = stmtCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    Alertas.mostrarAlertaError(null, "Error", "El usuario o correo ya están registrados.");
                    return false;
                }
            }

            // Insertar cliente
            String insertCliente = """
                INSERT INTO cliente (nombre, apellido1, apellido2, fecha_nacimiento, telefono, correo_electronico, 
                                     codigo_postal, localidad, provincia, pais, tipo_via, nombre_via, fecha_alta)
                VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)
            """;
            int clienteId;
            try (PreparedStatement stmtCliente = conn.prepareStatement(insertCliente, new String[]{"cliente_id"})) {
                stmtCliente.setString(1, usuario.getNombre());
                stmtCliente.setString(2, usuario.getApellido1());
                stmtCliente.setString(3, usuario.getApellido2());
                stmtCliente.setString(4, usuario.getFechaNacimiento()); // formato: YYYY-MM-DD
                stmtCliente.setString(5, usuario.getTelefono());
                stmtCliente.setString(6, usuario.getCorreoElectronico());
                stmtCliente.setString(7, usuario.getCodigoPostal());
                stmtCliente.setString(8, usuario.getLocalidad());
                stmtCliente.setString(9, usuario.getProvincia());
                stmtCliente.setString(10, usuario.getPais());
                stmtCliente.setString(11, usuario.getTipoVia());
                stmtCliente.setString(12, usuario.getNombreVia());

                stmtCliente.executeUpdate();
                ResultSet rsCliente = stmtCliente.getGeneratedKeys();
                if (rsCliente.next()) {
                    clienteId = rsCliente.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del cliente.");
                }
            }

            // Insertar usuario_cliente
            String insertUsuario = """
                INSERT INTO usuario_cliente (nombre_usuario, contrasena, correo_electronico, cliente_id, fecha_alta)
                VALUES (?, ?, ?, ?, SYSDATE)
            """;
            try (PreparedStatement stmtUsuario = conn.prepareStatement(insertUsuario)) {
                stmtUsuario.setString(1, usuario.getNombreUsuario());
                stmtUsuario.setString(2, usuario.getContrasena());
                stmtUsuario.setString(3, usuario.getCorreoElectronico());
                stmtUsuario.setInt(4, clienteId);
                stmtUsuario.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError(null, "Error", "No se pudo registrar al usuario.");
            return false;
        }
    }
}