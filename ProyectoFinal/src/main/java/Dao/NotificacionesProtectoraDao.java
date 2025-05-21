package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.NotificacionProtectora;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificacionesProtectoraDao {

    public static ObservableList<NotificacionProtectora> obtenerNotificaciones() {
        ObservableList<NotificacionProtectora> lista = FXCollections.observableArrayList();

        String sql = "SELECT informacion FROM notificaciones_protectora";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String info = resultSet.getString("informacion");
                lista.add(new NotificacionProtectora(info));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
