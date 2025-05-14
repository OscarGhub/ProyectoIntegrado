package controlador;

import Dao.PopUpDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Alertas;
import modelo.CitasInfo;

public class ModificarCitaPopUpController {

    @FXML private DatePicker datePickerFechaCita;
    @FXML private TextField txtHoraCita;
    @FXML private TextField txtEstado;

    private CitasInfo citaOriginal;

    public void setDatosCita(CitasInfo cita) {
        this.citaOriginal = cita;

        datePickerFechaCita.setValue(cita.getFechaCita());
        txtHoraCita.setText(cita.getHoraCita());
        txtEstado.setText(cita.getEstado());
    }

    @FXML
    void btnGuardarCambios() {
        try {
            // Crear objeto con los datos modificados
            CitasInfo citaModificada = new CitasInfo();
            citaModificada.setFechaCita(datePickerFechaCita.getValue());
            citaModificada.setHoraCita(txtHoraCita.getText());
            citaModificada.setEstado(txtEstado.getText());

            // Guardar cambios en la base de datos
            new PopUpDAO().actualizarCita(citaOriginal, citaModificada);

            // Cerrar ventana
            Stage stage = (Stage) datePickerFechaCita.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alertas.mostrarAlertaError("Error","Error al guardar los cambios.","No se pudieron guardar los cambios.");
        }
    }

}
