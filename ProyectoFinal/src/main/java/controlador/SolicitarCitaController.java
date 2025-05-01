package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.FormularioAdopcion;
import modelo.FormularioCita;
import modelo.TipoVia;
import modelo.Ventanas;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class SolicitarCitaController implements Initializable {

    @FXML
    private TextField cajaTextCorreoElectronico;

    @FXML
    private DatePicker dataPickerFechaCita;

    @FXML
    private TextField cajaTextDonacion;

    // Método que se ejecuta cuando el usuario hace clic en "Enviar"
    @FXML
    void btnEnviarAc(ActionEvent event) {
        // Recoger los valores de los campos de texto
        String correo = cajaTextCorreoElectronico.getText();
        String fechaCita = dataPickerFechaCita.getValue().toString();
        String donacion = cajaTextDonacion.getText();

        // Crear el objeto Formulario
        FormularioCita formulario = new FormularioCita(correo, fechaCita, donacion);
        formulario.setCorreo_electronico(correo);
        formulario.setDonacion(donacion);
        formulario.setFecha_cita(fechaCita);

        guardarEnBaseDeDatos(formulario);
    }

    private void guardarEnBaseDeDatos(FormularioCita formulario) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String usuario = "C##PROYECTOINTEGRADO";
        String contrasena = "123456";

        String sql = "INSERT INTO CITA (correo_electronico, fecha_cita, donacion) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String correoElectronico = formulario.getCorreo_electronico();

            // Verificar si el correo existe en la tabla CLIENTE
            String verificaCorreoSQL = "SELECT COUNT(*) FROM CLIENTE WHERE correo_electronico = ?";
            try (PreparedStatement verificaCorreoStmt = connection.prepareStatement(verificaCorreoSQL)) {
                verificaCorreoStmt.setString(1, correoElectronico);
                ResultSet rs = verificaCorreoStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count == 0) {
                    System.out.println("El correo electrónico no existe en la tabla CLIENTE.");
                    return; // Detener la ejecución si el correo no está registrado
                }
            }

            // Convertir la fecha de la cita
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(formulario.getFecha_cita());
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            String donacion = formulario.getDonacion();

            // Insertar la cita en la base de datos
            preparedStatement.setString(1, correoElectronico);
            preparedStatement.setDate(2, fechaSQL);
            preparedStatement.setString(3, donacion);

            preparedStatement.executeUpdate();

            System.out.println("Datos insertados correctamente en la base de datos");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar los datos en la base de datos");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al convertir la fecha: " + formulario.getFecha_cita());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
