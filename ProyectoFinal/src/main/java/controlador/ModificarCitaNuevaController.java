package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Dao.ModificarCitaDAO;
import modelo.CitasInfo;

public class ModificarCitaNuevaController {

    @FXML
    private TextField txtFechaCita;

    @FXML
    private TextField txtHoraCita;

    @FXML
    private TextField txtEstado;

    @FXML
    private Button btnActualizar;

    private int citaId;  // Asegúrate de pasar el ID de la cita

    // Este método se llamará para cargar los datos de la cita seleccionada en la nueva ventana
    public void cargarDatosCita(CitasInfo cita) {
        this.citaId = cita.getCitaId();
        txtFechaCita.setText(cita.getFechaCita().toString());
        txtHoraCita.setText(cita.getHoraCita());
        txtEstado.setText(cita.getEstado());
    }

    // Acción al hacer clic en el botón de actualizar
    @FXML
    private void actualizarCita() {
        String nuevaFecha = txtFechaCita.getText();
        String nuevaHora = txtHoraCita.getText();
        String nuevoEstado = txtEstado.getText();

        // Llamar a ModificarCitaDAO para actualizar los datos en la base de datos
        boolean actualizado = ModificarCitaDAO.actualizarCita(citaId, nuevaFecha, nuevaHora, nuevoEstado);

        if (actualizado) {
            // Si la cita se actualizó correctamente
            System.out.println("Cita actualizada exitosamente.");
        } else {
            // Si hubo algún error
            System.out.println("Hubo un error al actualizar la cita.");
        }

        // Cierra la ventana de modificación
        btnActualizar.getScene().getWindow().hide();
    }
}
