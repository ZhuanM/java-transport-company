package Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carriage> carriages;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @ManyToMany(mappedBy = "companies")
    private List<Customer> customers;

    @Column(name = "profit")
    private BigDecimal profit;


    // CONSTRUCTORS
    public Company() { }

    public Company(long id, String name) {
        this.id = id;
        this.name = name;

        this.carriages = new ArrayList<Carriage>();
        this.employees = new ArrayList<Employee>();
        this.vehicles = new ArrayList<Vehicle>();
        this.customers = new ArrayList<Customer>();
    }

    public Company(long id, String name, List<Employee> employees, List<Vehicle> vehicles, List<Customer> customers, List<Carriage> carriages, BigDecimal profit) {
        this.id = id;
        this.name = name;
        this.carriages = carriages;
        this.employees = employees;
        this.vehicles = vehicles;
        this.customers = customers;
        this.profit = profit;
    }


    // GETTERS
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public BigDecimal getProfit() {
        return profit;
    }


    // SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profit=" + profit +
                '}';
    }
}
