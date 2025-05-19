package controlador;

import Dao.ClienteCitaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Alertas;
import modelo.CitasInfo;
import utils.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarCitaClienteController {

    private java.time.LocalDate fechaCitaOriginal;
    private String horaCitaOriginal;

    @FXML
    private DatePicker fechaCita;
    @FXML
    private TextField horaCita;

    private CitasInfo citaSeleccionada;

    // Este método se llama para asignar la cita seleccionada y llenar los campos con la información existente.
    public void setCitaSeleccionada(CitasInfo cita) {
        this.citaSeleccionada = cita;
        this.fechaCitaOriginal = cita.getFechaCita();
        this.horaCitaOriginal = cita.getHoraCita();

        fechaCita.setValue(cita.getFechaCita());
        horaCita.setText(cita.getHoraCita());
    }

    private boolean esHoraPermitida(String horaIngresada) {
        for (modelo.Hora hora : modelo.Hora.values()) {
            if (hora.getHoraTexto().equals(horaIngresada)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void guardarCambios() {

        Alertas alerta = new Alertas();

        String horaIngresada = horaCita.getText().trim();

        if (fechaCita.getValue() == null || horaIngresada.isEmpty()) {
            alerta.mostrarAlertaError("Error", "Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        if (!esHoraPermitida(horaIngresada)) {
            alerta.mostrarAlertaError("Error", "Hora no válida", "La hora ingresada no es válida. Por favor, seleccione una hora de la lista.");
            return;
        }

        citaSeleccionada.setFechaCita(fechaCita.getValue());
        citaSeleccionada.setHoraCita(horaIngresada);

        if (!"Pendiente".equals(citaSeleccionada.getEstado())) {
            citaSeleccionada.setEstado("Pendiente");
        }

        guardarCambiosCita(citaSeleccionada);
    }

    private void guardarCambiosCita(CitasInfo cita) {
        try {

            ClienteCitaDAO dao = new ClienteCitaDAO();

            boolean actualizado = dao.actualizarCita(cita, fechaCitaOriginal, horaCitaOriginal);

            if (actualizado) {
                Alertas.mostrarConfirmacion("Confirmación", "Cita modificada, los cambios se han guardado correctamente, reinicie la página para ver los cambios.");
            } else {
                Alertas.mostrarAlertaError("ERROR", "No se pudo modificar la cita", "No se encontró una cita con los datos proporcionados.");
            }
        } catch (Exception e) {
            Logger.getLogger(ModificarCitaClienteController.class.getName()).log(Level.SEVERE, "Error al actualizar la cita", e);
            Alertas.mostrarAlertaError("Error", "Error de base de datos", "Hubo un problema al guardar los cambios. Por favor, intente nuevamente.");
        }

        cargarDatos();
    }

    private void cargarDatos() {
        try {

            ClienteCitaDAO dao = new ClienteCitaDAO();

            List<CitasInfo> citas = dao.cargarCitas();

            for (CitasInfo cita : citas) {
                System.out.println(cita.getNombreCliente() + " - " + cita.getFechaCita());
            }

        } catch (SQLException e) {
            Logger.getLogger(ModificarCitaClienteController.class.getName()).log(Level.SEVERE, "Error al cargar los datos", e);
        }
    }

    @FXML
    private void cancelarEdicion() {
        fechaCita.setValue(null);
        horaCita.setText("");
        fechaCita.getScene().getWindow().hide();
    }
}
