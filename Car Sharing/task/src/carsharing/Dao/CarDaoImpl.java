package carsharing.Dao;

import carsharing.service.JDBCUtilityCar;
import carsharing.service.JDBCUtilityCompany;

import java.util.List;

public class CarDaoImpl implements CarDao{

    private JDBCUtilityCar utility;
    private JDBCUtilityCompany utilityCompany;

    public CarDaoImpl(JDBCUtilityCar utility,
                      JDBCUtilityCompany utilityCompany) {
        this.utility = utility;
        this.utilityCompany = utilityCompany;
    }
    @Override
    public void createTable() {
        utility.createTable();
    }

    @Override
    public List<Car> getAllCarsCompany(String nameCompany) {
        return utility.selectAllCarsFromCompany(
                utilityCompany.findIdCompanyByName(nameCompany)
        );
    }

    @Override
    public int createCar(Car car) {
        return utility.createCar(car);
    }

    @Override
    public Car selectCarById(int id) {
        return utility.selectCarById(id);
    }
}
