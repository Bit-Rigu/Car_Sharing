package carsharing.Dao;

import carsharing.service.JDBCUtilityCustomer;

import java.util.List;
import java.util.Set;

public class CustomerDaoImpl implements CustomerDao{

    private JDBCUtilityCustomer utility;

    public CustomerDaoImpl(JDBCUtilityCustomer utilityCustomer) {
        this.utility = utilityCustomer;
    }

    @Override
    public void createTable() {
        utility.createTable();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return utility.selectAllCustomers();
    }

    @Override
    public int createCustomer(String name) {
        return utility.createCustomer(name);
    }

    @Override
    public void setRentedCarId(int idCustomer, int idCar) {
        utility.updateRentedCarId(idCustomer, idCar);
    }

    @Override
    public Customer selectCustomerById(int id) {
        return utility.selectCustomerById(id);
    }

    @Override
    public void returnCar(int id) {
        utility.returnRentedCar(id);
    }

    @Override
    public Set<Integer> returnRentedCarId() {
        return utility.returnRentedCarsId();
    }
}
