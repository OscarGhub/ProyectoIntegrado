package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Dao.*;
import utils.ConnectionManager;

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

    @FXML
    private ComboBox<Perro> perroCita;

    // Método que se ejecuta cuando el usuario hace clic en "Enviar"
    @FXML
    void btnEnviarAc(ActionEvent event) {
        String correo = cajaTextCorreoElectronico.getText();
        String fechaCita = dataPickerFechaCita.getValue().toString();
        String donacion = cajaTextDonacion.getText();
        String horaSeleccionada = horaCita.getValue();
        Perro perroSeleccionado = perroCita.getValue();

        if (perroSeleccionado == null) {
            Alertas.mostrarAlertaError("Error", "Debes seleccionar un perro.", "Selecciona un perro de la lista.");
            return;
        }

        if (!donacion.isBlank()) {
            try {
                double donacionValor = Double.parseDouble(donacion);
                if (donacionValor < 3.0) {
                    Alertas.mostrarAlertaError("Donación insuficiente", "Donación inválida.", "Si va a donar la donación debe ser de al menos 3 euros.");
                    return;
                }
            } catch (NumberFormatException e) {
                Alertas.mostrarAlertaError("Error de formato", "Donación no válida.", "Introduce una cantidad numérica o deja el campo vacío.");
                return;
            }
        }

        String consulta = "SELECT COUNT(*) AS total, " +
                "SUM(CASE WHEN hora_cita = '" + horaSeleccionada + "' THEN 1 ELSE 0 END) AS enEsaHora " +
                "FROM cita " +
                "WHERE perro_id = " + perroSeleccionado.getPerro_id() +
                " AND fecha_cita = TO_DATE('" + fechaCita + "', 'YYYY-MM-DD')";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            if (rs.next()) {
                int totalCitas = rs.getInt("total");
                int citasEnEsaHora = rs.getInt("enEsaHora");

                if (citasEnEsaHora > 0) {
                    Alertas.mostrarAlertaError("Conflicto de horario", "Este perro ya tiene una cita a esa hora.", "Elige otra hora.");
                    return;
                }

                if (totalCitas >= 3) {
                    Alertas.mostrarAlertaError("Cupo diario lleno", "Este perro ya tiene 3 citas ese día.", "Elige otra fecha.");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError("Error", "No se pudo verificar la disponibilidad del perro.", "Intenta de nuevo más tarde.");
            return;
        }

        // Guardar la cita si pasa las validaciones
        FormularioCita formulario = new FormularioCita(
                correo, fechaCita, donacion, horaSeleccionada, perroSeleccionado
        );

        SolicitarCitaDAO citaDAO = new SolicitarCitaDAO();
        boolean resultado = citaDAO.insertarCita(formulario);

        if (resultado) {
            Alertas.mostrarConfirmacion("Cita registrada", "La cita se ha guardado correctamente.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Horas
        horaCita.setItems(FXCollections.observableArrayList(
                Arrays.stream(Hora.values())
                        .map(Hora::getHoraTexto)
                        .collect(Collectors.toList())
        ));
        horaCita.setValue(Hora.HORA_08.getHoraTexto());

        // Perros no adoptados
        ObservableList<Perro> perros = FXCollections.observableArrayList();
        String sql = "SELECT perro_id, nombre FROM perro WHERE adoptado = 'No'";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("perro_id");
                String nombre = rs.getString("nombre");
                perros.add(new Perro(id, nombre));
            }

            perroCita.setItems(perros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
