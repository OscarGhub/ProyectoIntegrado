package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Perro;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PerroDAO {

    public ObservableList<Perro> obtenerPerrosNoAdoptados() {
        ObservableList<Perro> lista = FXCollections.observableArrayList();
        String sql = "SELECT perro_id, nombre FROM perro WHERE adoptado = 'No'";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("perro_id");
                String nombre = rs.getString("nombre");
                lista.add(new Perro(id, nombre));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
