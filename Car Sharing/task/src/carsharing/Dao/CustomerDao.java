package carsharing.Dao;

import java.util.List;
import java.util.Set;

public interface CustomerDao {
    void createTable();
    List<Customer> getAllCustomers();
    int createCustomer(String name);
    void setRentedCarId(int idCustomer, int idCar);
    Customer selectCustomerById(int id);
    void returnCar(int id);
    Set<Integer> returnRentedCarId();
}
