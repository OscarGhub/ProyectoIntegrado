package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrutasDAOImpl implements FrutasDAO {

    Connection con;
    public FrutasDAOImpl(Connection con) {
        this.con = con;
    }

    public void MostrartarFrutas(){

        String sql1="Select * from frutas";
        try{

            PreparedStatement preparedStatement = con.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){

                System.out.println("\nNombre: "+resultSet.getString("nombre"));
                System.out.println("Color:" +resultSet.getString("color"));
                System.out.println("Precio:" +resultSet.getInt("precio_kg"));
                System.out.println("Proveedor:" +resultSet.getInt("proveedor_id")+"\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
