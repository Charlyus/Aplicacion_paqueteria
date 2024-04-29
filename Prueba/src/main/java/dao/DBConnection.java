/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author carlos
 */
class DBConnection {
    private static DBConnection instance;

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String USER = "IPC2";
    private final String PASSWORD = "123456789";
    private final String DATABASE = "Prueba";
    private final String URL = "jdbc:mysql://" +HOST+ ":" +PORT+  "/" + DATABASE;

    private Connection connection;


    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if(connection == null) {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion creada");
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }
}
