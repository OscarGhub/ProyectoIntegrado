package Dao;

import modelo.NotificacionProtectora;
import modelo.Notificaciones;
import modelo.UsuarioSesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacionesClienteDao {

    public static ObservableList<Notificaciones> obtenerNotificaciones() {
        ObservableList<Notificaciones> lista = FXCollections.observableArrayList();

        String sql = "SELECT informacion FROM notificaciones_cliente ORDER BY fecha_alta DESC";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String info = resultSet.getString("informacion");
                lista.add(new Notificaciones(info));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public static void insertarNotificacion(String mensaje, String nuevaCita) {
        String sql = "INSERT INTO notificaciones_cliente (nueva_notificacion,informacion, fecha_alta) VALUES (?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, nuevaCita);
            stmt.setString(2, mensaje);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
