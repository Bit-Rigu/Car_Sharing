package carsharing.service;

import carsharing.Dao.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtilityCompany implements SQLUtilityCompany{

    private DatabaseConnection connection;

    public   JDBCUtilityCompany(DatabaseConnection connection) {
            this.connection = connection;
        }

    public void createTable() {
        try (Statement stmt = connection.getConnection().createStatement()){
            String sql =  "CREATE TABLE company " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL)";
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List<Company> selectAllCompanies() {
        List<Company> list = new ArrayList<>();
        try (Statement stmt = connection.getConnection().createStatement()) {
            String sql = "SELECT * FROM company";
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()) {
                list.add( new Company(
                        res.getInt("id"),
                        res.getString("name")));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }


    public int createCompany(Company company) {
        int numberCompany = 0;
        String sql = "INSERT INTO company(name) VALUES (?)";
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, company.getName());
            numberCompany = stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return numberCompany;
    }

    @Override
    public Company selectCompanyById(int id) {
        Company company = null;
        try (Statement stmt = connection.getConnection().createStatement()) {
            String sql = "SELECT id, name FROM company " +
                    "WHERE id = " + id;
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()) {
                company = new Company(
                        res.getInt("id"),
                        res.getString("name"));
            }
            } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return company;
    }

    @Override
    public int findIdCompanyByName(String name) {
        int id = 0;
        String sql = "SELECT id FROM company WHERE name = (?)";
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                id = res.getInt("id");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
}

