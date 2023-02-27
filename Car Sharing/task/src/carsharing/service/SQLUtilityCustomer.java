package carsharing.service;

import carsharing.Dao.Customer;

import java.util.List;
import java.util.Set;

public interface SQLUtilityCustomer {
    void createTable();
    List<Customer> selectAllCustomers();
    int createCustomer(String name);
    void updateRentedCarId(int idCustomer, int idCar);
    Customer selectCustomerById(int id);
    void returnRentedCar(int id);
    Set<Integer> returnRentedCarsId();
}
