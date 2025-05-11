package controlador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.*;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

import java.util.Date;
import java.util.stream.Collectors;

public class SolicitarCitaController implements Initializable {

    @FXML
    private TextField cajaTextCorreoElectronico;

    @FXML
    private DatePicker dataPickerFechaCita;

    @FXML
    private TextField cajaTextDonacion;

    @FXML
    private ComboBox<String> horaCita;

    @FXML
    private Button btnEnviar;

    // Método que se ejecuta cuando el usuario hace clic en "Enviar"
    @FXML
    void btnEnviarAc(ActionEvent event) {
        // Recoger los valores de los campos de texto
        String correo = cajaTextCorreoElectronico.getText();
        String fechaCita = dataPickerFechaCita.getValue().toString();
        String donacion = cajaTextDonacion.getText();

        // Obtener la hora seleccionada del ComboBox
        String horaSeleccionada = horaCita.getValue();  // La hora seleccionada es un String como "08:00"

        // Crear el objeto FormularioCita
        FormularioCita formulario = new FormularioCita(correo, fechaCita, donacion, horaSeleccionada);
        formulario.setCorreo_electronico(correo);
        formulario.setDonacion(donacion);
        formulario.setFecha_cita(fechaCita);
        formulario.setHora_cita(horaSeleccionada);  // Establecer la hora seleccionada

        guardarEnBaseDeDatos(formulario);
    }

    private void guardarEnBaseDeDatos(FormularioCita formulario) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String usuario = "C##PROYECTOINTEGRADO";
        String contrasena = "123456";

        String sql = "INSERT INTO CITA (cliente_id, correo_electronico, fecha_cita, donacion, hora_cita) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String correoElectronico = formulario.getCorreo_electronico();

            // 1. Obtener cliente_id desde correo
            String obtieneClienteIdSQL = "SELECT cliente_id FROM CLIENTE WHERE correo_electronico = ?";
            int clienteId = -1;

            try (PreparedStatement obtieneClienteStmt = connection.prepareStatement(obtieneClienteIdSQL)) {
                obtieneClienteStmt.setString(1, correoElectronico);
                ResultSet rs = obtieneClienteStmt.executeQuery();
                if (rs.next()) {
                    clienteId = rs.getInt("cliente_id");
                } else {
                    Alertas.mostrarAlertaWarningGeneral("Correo no encontrado", "El correo electrónico ingresado no está registrado como cliente.");
                    return;
                }
            }

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
            Date fechaActual = new Date();

            if (fecha.before(fechaActual)) {
                Alertas.mostrarAlertaWarningGeneral("Fecha no válida", "La fecha seleccionada no puede ser anterior a la fecha actual.");
                return;
            }

            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());


            String donacion = formulario.getDonacion();

            // Insertar la cita en la base de datos
            preparedStatement.setInt(1, clienteId);
            preparedStatement.setString(2, correoElectronico);
            preparedStatement.setDate(3, fechaSQL);
            preparedStatement.setString(4, donacion);
            preparedStatement.setString(5, formulario.getHora_cita());

            preparedStatement.executeUpdate();

            Alertas.mostrarConfirmacion("Cita registrada", "La cita se ha guardado correctamente.");


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
        horaCita.setItems(FXCollections.observableArrayList(
                Arrays.stream(Hora.values())
                        .map(Hora::getHoraTexto)
                        .collect(Collectors.toList())
        ));

        // Si quieres seleccionar un valor por defecto, por ejemplo, HORA_08
        horaCita.setValue(Hora.HORA_08.getHoraTexto());
    }

}
