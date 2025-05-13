package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.CitasInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ModificarCitaPopUpController {

    @FXML private DatePicker datePickerFechaCita;
    @FXML private TextField txtHoraCita;
    @FXML private TextField txtEstado;

    private CitasInfo cita;

    public void setDatosCita(CitasInfo cita) {
        this.cita = cita;

        datePickerFechaCita.setValue(cita.getFechaCita());
        txtHoraCita.setText(cita.getHoraCita());
        txtEstado.setText(cita.getEstado());
    }

    @FXML
    void btnGuardarCambios() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "C##PROYECTOINTEGRADO";
            String password = "123456";

            String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, estado = ? " +
                    "WHERE fecha_cita = ? AND hora_cita = ?";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setDate(1, java.sql.Date.valueOf(datePickerFechaCita.getValue()));
                stmt.setString(2, txtHoraCita.getText());
                stmt.setString(3, txtEstado.getText());

                stmt.setDate(4, java.sql.Date.valueOf(cita.getFechaCita()));
                stmt.setString(5, cita.getHoraCita());

                stmt.executeUpdate();
            }

            // Cierra la ventana al guardar
            Stage stage = (Stage) datePickerFechaCita.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
