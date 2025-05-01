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

        String sql = "INSERT INTO CITA (correo, fecha_cita, donacion) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String correoElectronico = formulario.getCorreo_electronico();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(formulario.getFecha_cita());
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            String donacion = formulario.getDonacion();

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, correoElectronico);
            pstmt.setDate(2, fechaSQL);
            pstmt.setString(3, donacion);
            pstmt.executeUpdate();

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
