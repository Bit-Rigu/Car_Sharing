package carsharing;


import carsharing.controller.MainController;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        MainController controller = new MainController(args);
        controller.createTableCompany();
        controller.createTableCar();
        controller.createTableCustomer();
        controller.processingUserInterface();
    }
}