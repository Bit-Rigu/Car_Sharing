package carsharing.service;

import carsharing.CommandLineChecker;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable{

    private Connection connection = null;

    public DatabaseConnection(String[] nameDatabase) {
        CommandLineChecker checker = new CommandLineChecker();
        String nameDB = checker.getName(nameDatabase);
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:h2:./src/carsharing/db/" +
                            ((nameDB != null) ? nameDB : "anything"),
                    "", "");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
