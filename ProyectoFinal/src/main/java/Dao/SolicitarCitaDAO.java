package Dao;

import modelo.FormularioCita;
import modelo.Alertas;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolicitarCitaDAO {

    private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String usuario = "C##PROYECTOINTEGRADO";
    private final String contrasena = "123456";

    public boolean insertarCita(FormularioCita formulario) {
        String sql = "INSERT INTO CITA (cliente_id, correo_electronico, fecha_cita, donacion, hora_cita, perro_id, nombre_perro) VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String correo = formulario.getCorreo_electronico();

            // Obtener cliente_id
            int clienteId = obtenerClienteIdPorCorreo(connection, correo);
            if (clienteId == -1) {
                Alertas.mostrarAlertaWarningGeneral("Correo no encontrado", "El correo electrónico no está registrado.");
                return false;
            }

            // Validar fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(formulario.getFecha_cita());
            Date hoy = new Date();

            if (fecha.before(hoy)) {
                Alertas.mostrarAlertaWarningGeneral("Fecha no válida", "La fecha seleccionada no puede ser anterior a la actual.");
                return false;
            }

            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            preparedStatement.setInt(1, clienteId);
            preparedStatement.setString(2, correo);
            preparedStatement.setDate(3, fechaSQL);
            preparedStatement.setString(4, formulario.getDonacion());
            preparedStatement.setString(5, formulario.getHora_cita());
            preparedStatement.setInt(6, formulario.getPerro().getPerro_id());
            preparedStatement.setString(7, formulario.getPerro().getNombre());

            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private int obtenerClienteIdPorCorreo(Connection connection, String correo) throws SQLException {
        String sql = "SELECT cliente_id FROM CLIENTE WHERE correo_electronico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cliente_id");
            } else {
                return -1;
            }
        }
    }


}

