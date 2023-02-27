package carsharing.Dao;

public class Customer {
    private int id;
    private String name;
    private Integer rentedCarId;

    public Customer(String name) {
        this.name = name;
    }
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Customer(int id, String name, Integer rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }

    public int getId() { return id;}
    public String getName() {return name;}
    public Integer getRentedCarId() {return rentedCarId;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setRentedCarId(int rentedCarId) {this.rentedCarId = rentedCarId;}
}
