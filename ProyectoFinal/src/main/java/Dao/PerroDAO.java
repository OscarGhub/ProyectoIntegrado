package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Perro;
import modelo.Sexo;
import utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;

public class PerroDAO {

    public static ObservableList<Perro> obtenerPerrosDisponibles() {
        ObservableList<Perro> listaPerros = FXCollections.observableArrayList();

        String sql = """
            SELECT p.perro_id, p.nombre, r.nombre AS raza, p.sexo, p.adoptado,
                   p.fecha_alta, p.fecha_modificacion
            FROM perro p
            LEFT JOIN raza r ON p.raza = r.id_raza
            WHERE p.adoptado = 'No'
        """;

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("perro_id");
                String nombre = rs.getString("nombre");
                String raza = rs.getString("raza");
                String sexoStr = rs.getString("sexo");
                boolean adoptado = "Si".equalsIgnoreCase(rs.getString("adoptado"));
                LocalDate fechaAlta = rs.getDate("fecha_alta").toLocalDate();
                LocalDate fechaMod = rs.getDate("fecha_modificacion").toLocalDate();

                Sexo sexo = sexoStr.equalsIgnoreCase("M") ? Sexo.Masculino: Sexo.Feminino;

                Perro perro = new Perro(id, nombre, raza, sexo, adoptado, fechaAlta, fechaMod);
                listaPerros.add(perro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener la lista de perros disponibles.");
        }

        return listaPerros;
    }
}
