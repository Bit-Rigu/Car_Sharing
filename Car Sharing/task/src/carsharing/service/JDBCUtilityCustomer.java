package carsharing.service;

import carsharing.Dao.Company;
import carsharing.Dao.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JDBCUtilityCustomer implements SQLUtilityCustomer{

    private DatabaseConnection connection;

    public JDBCUtilityCustomer(DatabaseConnection connection) {
        this.connection = connection;
    }
    @Override
    public void createTable() {
        try (Statement stmt = connection.getConnection().createStatement()){
            String sql =  "CREATE TABLE customer " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) UNIQUE NOT NULL," +
                    "rented_car_id INTEGER DEFAULT NULL," +
                    "CONSTRAINT fk_car FOREIGN KEY" +
                    "(rented_car_id) REFERENCES car(id))";
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Customer> selectAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (Statement stmt = connection.getConnection().createStatement()) {
            String sql = "SELECT * FROM customer";
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()) {
                list.add( new Customer(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("rented_car_id")));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public int createCustomer(String name) {
        int numberCustomer = 0;
        String sql = "INSERT INTO customer(name) VALUES (?)";
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            numberCustomer = stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return numberCustomer;
    }

    @Override
    public void updateRentedCarId(int idCustomer, int idCar) {
        String sql = "UPDATE customer " +
                "SET rented_car_id = (?) WHERE id = (?)";
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idCar);
            stmt.setInt(2, idCustomer);
            stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Customer selectCustomerById(int id) {
        Customer customer = null;
        try (Statement stmt = connection.getConnection().createStatement()) {
            String sql = "SELECT * FROM customer " +
                    "WHERE id = " + id;
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()) {
                customer = new Customer(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("rented_car_id"));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    @Override
    public void returnRentedCar(int id) {
        try (Statement stmt = connection.getConnection().createStatement()) {
            String sql = "UPDATE customer SET rented_car_id = NULL " +
                    "WHERE id = " + id;
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<Integer> returnRentedCarsId() {
        Set<Integer> set = new HashSet<>();
        try(Statement stmt = connection.getConnection().createStatement()) {
            String sql = "SELECT * FROM customer " +
                    "WHERE rented_car_id > 0";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                set.add(rs.getInt("rented_car_id"));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return set;
    }
}
