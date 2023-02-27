package carsharing.service;

import carsharing.Dao.Car;
import carsharing.Dao.Company;
import carsharing.Dao.Customer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    private byte numberOfStepMenu = 1;
    private int numberOfCompany;
    private int numberOfCustomer;
    private final String[] phrasesFirstStepMenu = {
            "1. Log in as a manager",
            "2. Log in as a customer",
            "3. Create a customer",
            "0. Exit"};
    private final String[] phrasesSecondStepMenu = {
            "1. Company list",
            "2. Create a company",
            "0. Back"};
    private final String[] phrasesThirdStepMenu = {
            "1. Car list",
            "2. Create a car",
            "0. Back"};
    private final String[] phrasesRentCarMenu = {
            "1. Rent a car",
            "2. Return a rented car",
            "3. My rented car",
            "0. Back"
    };
    public void setNumberOfCompany(int numberOfCompany) {
        this.numberOfCompany = numberOfCompany;
    }

    public int getNumberOfCompany() {
        return numberOfCompany;
    }

    public void setNumberOfCustomer(int numberOfCustomer) {this.numberOfCustomer = numberOfCustomer;}

    public int getNumberOfCustomer() {return numberOfCustomer;}


    @Override
    public void printMenu() {
        if (numberOfStepMenu == 1) {
            System.out.println();
            for (String firstStepMenu : phrasesFirstStepMenu) {
                System.out.println(firstStepMenu);
            }
        } else if (numberOfStepMenu == 2) {
            System.out.println();
            for (String secondStepMenu : phrasesSecondStepMenu) {
                System.out.println(secondStepMenu);
            }
        } else if (numberOfStepMenu == 3) {
            for (String thirdStepMenu : phrasesThirdStepMenu) {
                System.out.println(thirdStepMenu);
            }
        }
    }

    @Override
    public byte readUserByteResponse() {
        Scanner sc = new Scanner(System.in);
        return sc.nextByte();
    }

    @Override
    public int readUserIntResponse() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    @Override
    public void nextMenuAsManager() {
        numberOfStepMenu++;
    }

    @Override
    public void backMenuAsManager() {
        numberOfStepMenu--;
    }

    @Override
    public void printCompaniesList(List<Company> list) {
        System.out.println();
        if(list.isEmpty()) System.out.println("The company list is empty!");
        else {
            System.out.println("Choose a company:");
            printerCompanyList(list);
            System.out.println("0. Back");
        }
    }

    @Override
    public void printCarsListCompany(List<Car> list, String companyName) {
        if(list.isEmpty()) System.out.println("The car list is empty!");
        else {
            System.out.println(companyName + " cars:");
            printerCarsList(list);
        }
    }

    @Override
    public void printCreatingCompany() {
        System.out.println("Enter the company name:");
    }

    @Override
    public String readCreating() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public byte getNumberOfStepMenu() {
        return numberOfStepMenu;
    }

    @Override
    public void printFinishingCreatingCompany() {
        System.out.println("The company was created!");
    }

    @Override
    public void printCompanyNamePlusCompany(String name) {
        System.out.println();
        System.out.println(name + " company:");
    }

    @Override
    public void printCreatingCar() {
        System.out.println("Enter the car name:");
    }

    @Override
    public void printFinishingCreatingCar() {
        System.out.println("The car was added!");
    }

    @Override
    public void printCreatingCustomer() {
        System.out.println();
        System.out.println("Enter the customer name:");
    }

    @Override
    public void printFinishingCreatingCustomer() {
        System.out.println("The customer was added!");
    }

    @Override
    public void printCustomersList(List<Customer> list) {
        if(list.isEmpty()) System.out.println("The customer list is empty!");
        else {
            System.out.println();
            System.out.println("Customer list:");
            printerCustomerList(list);
            System.out.println("0. Back");
        }
    }

    @Override
    public void printRentCarMenu() {
        System.out.println();
        for (String str : phrasesRentCarMenu) {
            System.out.println(str);
        }
    }

    @Override
    public void printCarsListCompanyWithoutNameCompany(List<Car> list) {
        if(list.isEmpty()) System.out.println("The car list is empty!");
        else {
            System.out.println("Choose a car:");
            printerCarsList(list);
            System.out.println("0. Back");
        }
    }

    @Override
    public void printRentedCar(String name) {
        System.out.println("You rented '"+ name +"'");
    }

    @Override
    public void printViolationRentedCar() {
        System.out.println();
        System.out.println("You've already rented a car!");
    }

    @Override
    public void printNoAvailableCars(String nameCompany) {
        System.out.println();
        System.out.println("No available cars in the '"+ nameCompany +"' company");
    }

    @Override
    public void printViolationReturnedRentedCar() {
        System.out.println("You didn't rent a car!");
    }

    @Override
    public void printReturnCar() {
        System.out.println();
        System.out.println("You've returned a rented car!");
    }

    @Override
    public void printMenuMyRentedCar(String nameCompany, String nameCar) {
        System.out.println("Your rented car:");
        System.out.println(nameCar);
        System.out.println("Company:");
        System.out.println(nameCompany);
    }

    @Override
    public void printNoRentCarYet() {
        System.out.println();
        System.out.println("You didn't rent a car!");
    }

    private static void printerCustomerList(List<Customer> list) {
        for(int i = 0; i < list.size(); i++) {
            int temp = i + 1;
            System.out.println(temp + ". " + list.get(i).getName());
        }
    }


    private static void printerCompanyList(List<Company> list) {
        for(int i = 0; i < list.size(); i++) {
            int temp = i + 1;
            System.out.println(temp + ". " + list.get(i).getName());
        }
    }

    private static void printerCarsList(List<Car> list) {
        Collections.sort(list, Comparator.comparingInt(Car::getId));
        for(int i = 0; i < list.size(); i++) {
            int temp = i + 1;
            System.out.println(temp + ". " + list.get(i).getName());
        }
    }
}
