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

    @FXML
    private void guardarCambios() {
        // Verificar si los campos están vacíos
        if (fechaCita.getValue() == null || horaCita.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos Vacíos");
            alert.setContentText("Por favor, complete todos los campos.");
            alert.showAndWait();
        } else {
            // Asignar nuevos valores a la cita
            citaSeleccionada.setFechaCita(fechaCita.getValue());
            citaSeleccionada.setHoraCita(horaCita.getText());

            // Cambiar el estado a "Pendiente" si no está ya en ese estado
            if (!"Pendiente".equals(citaSeleccionada.getEstado())) {
                citaSeleccionada.setEstado("Pendiente");
            }

            // Llamar al método para guardar los cambios en la base de datos
            guardarCambiosCita(citaSeleccionada);
        }
    }

    private void guardarCambiosCita(CitasInfo cita) {
        try {
            // Crear una instancia del DAO
            ClienteCitaDAO dao = new ClienteCitaDAO();

            // Intentar actualizar la cita usando el DAO
            boolean actualizado = dao.actualizarCita(cita, fechaCitaOriginal, horaCitaOriginal);

            // Mostrar un mensaje de éxito o error
            if (actualizado) {
                Alertas.mostrarConfirmacion("Confirmación", "Cita modificada, los cambios se han guardado correctamente, reinicie la página para ver los cambios.");
            } else {
                Alertas.mostrarAlertaError("ERROR", "No se pudo modificar la cita", "No se encontró una cita con los datos proporcionados.");
            }
        } catch (Exception e) {
            Logger.getLogger(ModificarCitaClienteController.class.getName()).log(Level.SEVERE, "Error al actualizar la cita", e);
            Alertas.mostrarAlertaError("Error", "Error de base de datos", "Hubo un problema al guardar los cambios. Por favor, intente nuevamente.");
        }

        // Recargar los datos después de guardar
        cargarDatos();
    }

    // Ahora el controlador llama al método del DAO para cargar las citas
    private void cargarDatos() {
        try {
            // Crear una instancia del DAO
            ClienteCitaDAO dao = new ClienteCitaDAO();

            // Obtener la lista de citas
            List<CitasInfo> citas = dao.cargarCitas();

            // Aquí puedes hacer algo con las citas obtenidas, como actualizar una tabla o lista en la UI
            // Por ejemplo:
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
