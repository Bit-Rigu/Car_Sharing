package carsharing.service;

import carsharing.Dao.Car;
import carsharing.Dao.Company;
import carsharing.Dao.Customer;

import java.util.List;

public interface UserInterface {
    void printMenu();
    byte readUserByteResponse();
    int readUserIntResponse();
    void nextMenuAsManager();
    void backMenuAsManager();
    void printCompaniesList(List<Company> list);
    void printCarsListCompany(List<Car> list, String companyName);
    void printCreatingCompany();
    String readCreating();
    byte getNumberOfStepMenu();
    void printFinishingCreatingCompany();
    void printCompanyNamePlusCompany(String name);
    void printCreatingCar();
    void printFinishingCreatingCar();
    void printCreatingCustomer();
    void printFinishingCreatingCustomer();
    void printCustomersList(List<Customer> list);
    void printRentCarMenu();
    void printCarsListCompanyWithoutNameCompany(List<Car> list);
    void printRentedCar(String name);
    void printViolationRentedCar();
    void printNoAvailableCars(String nameCompany);
    void printViolationReturnedRentedCar();
    void printReturnCar();
    void printMenuMyRentedCar(String nameCompany, String nameCar);
    void printNoRentCarYet();
}
