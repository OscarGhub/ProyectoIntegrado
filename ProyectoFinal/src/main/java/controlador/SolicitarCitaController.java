package controlador;

import Dao.PerroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.*;
import Dao.SolicitarCitaDAO;
import utils.ConnectionManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;
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

    @FXML
    private ComboBox<Perro> perroCita;

    @FXML
    void btnEnviarAc(ActionEvent event) {
        String correo = cajaTextCorreoElectronico.getText();
        String fechaCita = dataPickerFechaCita.getValue() != null ? dataPickerFechaCita.getValue().toString() : null;
        String donacion = cajaTextDonacion.getText();
        String horaSeleccionada = horaCita.getValue();
        Perro perroSeleccionado = perroCita.getValue();

        if (correo == null || correo.isBlank() || fechaCita == null || horaSeleccionada == null || perroSeleccionado == null) {
            Alertas.mostrarAlertaError("Campos obligatorios", "Faltan datos para registrar la cita.", "Rellena todos los campos.");
            return;
        }

        if (!donacion.isBlank()) {
            try {
                double donacionValor = Double.parseDouble(donacion);
                if (donacionValor < 3.0) {
                    Alertas.mostrarAlertaError("Donación insuficiente", "Si deseas donar, la cantidad mínima es 3€.", null);
                    return;
                }
            } catch (NumberFormatException e) {
                Alertas.mostrarAlertaError("Donación inválida", "Introduce una cantidad numérica válida o deja el campo vacío.", null);
                return;
            }
        }

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
        horaCita.setItems(FXCollections.observableArrayList(
                Arrays.stream(Hora.values())
                        .map(Hora::getHoraTexto)
                        .collect(Collectors.toList())
        ));
        horaCita.setValue(Hora.HORA_08.getHoraTexto());

        // Obtener perros no adoptados desde DAO
        PerroDAO perroDAO = new PerroDAO();
        ObservableList<Perro> perros = perroDAO.obtenerPerrosNoAdoptados();
        perroCita.setItems(perros);
    }

}
