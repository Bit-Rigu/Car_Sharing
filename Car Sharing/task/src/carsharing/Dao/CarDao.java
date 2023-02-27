package carsharing.Dao;

import java.util.List;

public interface CarDao {
    void createTable();
    List<Car> getAllCarsCompany(String nameCompany);
    int createCar(Car car);
    Car selectCarById(int id);
}
