package controlador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Dao.*;

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

    // Metodo que se ejecuta cuando el usuario hace clic en "Enviar"
    @FXML
    void btnEnviarAc(ActionEvent event) {
        String correo = cajaTextCorreoElectronico.getText();
        String fechaCita = dataPickerFechaCita.getValue().toString();
        String donacion = cajaTextDonacion.getText();
        String horaSeleccionada = horaCita.getValue();

        FormularioCita formulario = new FormularioCita(correo, fechaCita, donacion, horaSeleccionada);

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
    }

}
