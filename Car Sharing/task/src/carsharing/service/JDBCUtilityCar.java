package carsharing.service;

import carsharing.Dao.Car;
import carsharing.Dao.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtilityCar implements SQLUtilityCar{

    private DatabaseConnection connection;

    public JDBCUtilityCar(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try(Statement stmt = connection.getConnection().createStatement()) {
            String sql = "CREATE TABLE car" +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(200) UNIQUE NOT NULL," +
                    "company_id INTEGER NOT NULL," +
                    "CONSTRAINT fk_company FOREIGN KEY" +
                    "(company_id) REFERENCES company(id))";
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int createCar(Car car) {
        int numberCar = 0;
        String sql = "INSERT INTO car(name, company_id) VALUES (?,?)";
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, car.getName());
            stmt.setInt(2, car.getCompanyId());
            numberCar = stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return numberCar;
    }

    @Override
    public List<Car> selectAllCarsFromCompany(int companyId) {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE company_id = " + companyId;
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()) {
                list.add( new Car(
                        res.getInt("id"),
                        res.getString("name"),
                        companyId));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Car selectCarById(int id) {
        Car car = null;
        try (Statement stmt = connection.getConnection().createStatement()) {
            String sql = "SELECT id, name, company_id FROM car " +
                    "WHERE id = " + id;
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()) {
                car = new Car(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("company_id"));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return car;
    }

}
