package com.Utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;

    private static ConnectionManager instance;
    private ConnectionManager() {
        Properties props = new Properties();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
            if(is == null){
                throw new FileNotFoundException("config.properties no encontrado");
            }
            props.load(is);
            URL = props.getProperty("db.url");
            USERNAME = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        System.out.println("- Conectando a la base de datos -");
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

}
