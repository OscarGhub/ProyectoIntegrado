package controlador;

import Dao.ModificarPerrosAniadirDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Ventanas;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificarPerrosAniadirController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxRaza;

    @FXML
    private DatePicker textFechaNacimiento;

    @FXML
    private TextField textNombre;

    @FXML
    private ComboBox<String> comboBoxSexo;

    @FXML
    void btnGuardarCambios(ActionEvent event) {
        String nombre = textNombre.getText();
        LocalDate fechaNacimiento = textFechaNacimiento.getValue();
        String raza = comboBoxRaza.getValue();
        String sexo = comboBoxSexo.getValue();

        if (nombre.isEmpty() || fechaNacimiento == null || raza == null || sexo == null) {
            JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.");
            return;
        }

        ModificarPerrosAniadirDao.insertarPerro(nombre, fechaNacimiento, raza, sexo);
        JOptionPane.showMessageDialog(null, "Perro añadido correctamente.");

        // Limpiar formulario
        textNombre.clear();
        textFechaNacimiento.setValue(null);
        comboBoxRaza.getSelectionModel().clearSelection();
        comboBoxSexo.getSelectionModel().clearSelection();
        try {
            Ventanas.cerrarVentana(event);
        } catch (Exception e) {
            Logger.getLogger(InicioControlador.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> razas = ModificarPerrosAniadirDao.obtenerRazas();
        comboBoxRaza.getItems().addAll(razas);

        comboBoxSexo.getItems().addAll("Masculino", "Feminino", "M", "F");
    }

}
