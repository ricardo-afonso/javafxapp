package org.academiadecodigo.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    Connection connection = null;

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection("localhost", "root", "");
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}


