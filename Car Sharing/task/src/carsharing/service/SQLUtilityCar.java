package carsharing.service;

import carsharing.Dao.Car;

import java.util.List;

public interface SQLUtilityCar {
    void createTable();
    int createCar(Car car);
    List<Car> selectAllCarsFromCompany(int idCompany);
    Car selectCarById(int id);
}
