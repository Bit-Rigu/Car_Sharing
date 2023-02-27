package carsharing.controller;

import carsharing.Dao.*;
import carsharing.service.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MainController {

    private ConsoleUserInterface console;
    private CompanyDaoImpl daoCompany;
    private CarDaoImpl daoCar;
    private CustomerDaoImpl daoCustomer;
    private DatabaseConnection connection;
    private JDBCUtilityCompany utilityCompany;
    private JDBCUtilityCar utilityCar;
    private JDBCUtilityCustomer utilityCustomer;
    private byte userResponse;

    public MainController(String[] args) throws SQLException {
        this.connection = new DatabaseConnection(args);
        connection.getConnection().setAutoCommit(true);
        this.utilityCompany = new JDBCUtilityCompany(connection);
        this.utilityCar = new JDBCUtilityCar(connection);
        this.utilityCustomer = new JDBCUtilityCustomer(connection);
        this.daoCompany = new CompanyDaoImpl(utilityCompany);
        this.daoCar = new CarDaoImpl(utilityCar, utilityCompany);
        this.daoCustomer = new CustomerDaoImpl(utilityCustomer);
        this.console = new ConsoleUserInterface();
    }

    public void createTableCompany() {
        daoCompany.createTable();
    }

    public void createTableCar() { daoCar.createTable(); }

    public void createTableCustomer() { daoCustomer.createTable();}

    public void processingUserInterface() {

        do {
            if (console.getNumberOfStepMenu() == 1) {
                console.printMenu();
                userResponse = console.readUserByteResponse();
                if (userResponse == 1) console.nextMenuAsManager();
                else if (userResponse == 2) {
                    int intUserResponse = 0;
                    console.printCustomersList(
                            daoCustomer.getAllCustomers());
                    if (!daoCustomer.getAllCustomers().isEmpty())
                        intUserResponse = console.readUserIntResponse();
                    if (intUserResponse == 0) ;
                    else {
                        do {
                            console.setNumberOfCustomer(intUserResponse);
                            console.printRentCarMenu();
                            userResponse = console.readUserByteResponse();
                            if (userResponse == 1) {
                                if (!(daoCustomer.selectCustomerById(
                                                console.getNumberOfCustomer())
                                        .getRentedCarId() == 0)) console.printViolationRentedCar();
                                else {
                                    console.printCompaniesList(
                                            daoCompany.getAllCompanies());
                                    if (daoCompany.getAllCompanies().isEmpty()) ;
                                    else {
                                        int responseCompany = console.readUserIntResponse();
                                        if (responseCompany == 0) ;
                                        else {
                                            console.setNumberOfCompany(responseCompany);
                                            String nameCompany = daoCompany.selectCompany(
                                                    console.getNumberOfCompany()).getName();
                                            if (daoCar.getAllCarsCompany(nameCompany).isEmpty())
                                                console.printNoAvailableCars(nameCompany);
                                            else {
                                                List<Car> list = daoCar.getAllCarsCompany(nameCompany);
                                                Set<Integer> set = daoCustomer.returnRentedCarId();
                                                List<Car> listWithoutRented = list.stream()
                                                        .filter(e -> !set.contains(e.getId()))
                                                        .collect(Collectors.toList());
                                                console.printCarsListCompanyWithoutNameCompany(
                                                        listWithoutRented);
                                                if(listWithoutRented.isEmpty()) ;
                                                else {
                                                    int responseNumberCarFromList = console.readUserIntResponse();
                                                    if (responseNumberCarFromList == 0) ;
                                                    else {
                                                        Car car = listWithoutRented.get(responseNumberCarFromList - 1);
                                                        daoCustomer.setRentedCarId(
                                                                console.getNumberOfCustomer(),
                                                                car.getId());
                                                        console.printRentedCar(daoCar.selectCarById(car.getId())
                                                                .getName());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (userResponse == 2) {
                                if (daoCustomer.selectCustomerById(console.getNumberOfCustomer())
                                        .getRentedCarId() == 0) console.printViolationReturnedRentedCar();
                                else {
                                    daoCustomer.returnCar(console.getNumberOfCustomer());
                                    console.printReturnCar();
                                }
                            } else if (userResponse == 3) {
                                if (daoCustomer.selectCustomerById(console.getNumberOfCustomer())
                                        .getRentedCarId() == 0) console.printNoRentCarYet();
                                else {
                                    Customer customer = daoCustomer
                                            .selectCustomerById(console.getNumberOfCustomer());
                                    Car car = daoCar.selectCarById(
                                            customer.getRentedCarId());
                                    Company company = daoCompany.selectCompany(
                                            car.getCompanyId());
                                    console.printMenuMyRentedCar(
                                            company.getName(),
                                            car.getName());
                                }
                            }
                        } while (userResponse != 0);
                    }
                }
                else if (userResponse == 3) {
                    console.printCreatingCustomer();
                    daoCustomer.createCustomer(console.readCreating());
                    console.printFinishingCreatingCustomer();
                }
                else if (userResponse == 0) break;
            }
            if (console.getNumberOfStepMenu() == 2) {
                console.printMenu();
                userResponse = console.readUserByteResponse();
                if(userResponse == 1) {
                        console.printCompaniesList(daoCompany.getAllCompanies());
                        if(!daoCompany.getAllCompanies().isEmpty()){
                            int temp = console.readUserIntResponse();
                            if (temp == 0) ;
                            else {
                                console.setNumberOfCompany(temp);
                                console.nextMenuAsManager();
                            }
                        }
                    } else if (userResponse == 2) {
                        console.printCreatingCompany();
                        daoCompany.createCompany(
                                new Company(1, console.readCreating()));
                        console.printFinishingCreatingCompany();
                    }
                    else if (userResponse == 0) console.backMenuAsManager();
            }
            if (console.getNumberOfStepMenu() == 3) {
                String nameCompany = daoCompany.selectCompany(
                        console.getNumberOfCompany()).getName();
                console.printCompanyNamePlusCompany(nameCompany);
                console.printMenu();
                userResponse = console.readUserByteResponse();
                if (userResponse == 1) {
                    console.printCarsListCompany(
                            daoCar.getAllCarsCompany(nameCompany),
                            nameCompany);
                } else if (userResponse == 2) {
                    console.printCreatingCar();
                    daoCar.createCar(new Car(
                            1,
                            console.readCreating(),
                            daoCompany.findIdCompanyByName(nameCompany)));
                    console.printFinishingCreatingCar();
                } else if (userResponse == 0) console.backMenuAsManager();
            }
        } while(true);
    }
}
