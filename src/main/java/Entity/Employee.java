package Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "employees")
    @Column(name = "qualification", nullable = false)
    private List<Qualification> qualifications;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "employee")
    private List<Carriage> carriages;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Transient
    private boolean isAvailable;

    // CONSTRUCTORS
    public Employee() { }

    public Employee(
            String name,
            List<Qualification> qualifications,
            Company company,
            BigDecimal salary
    ) {
        this.name = name;
        this.qualifications = qualifications;
        this.company = company;
        this.carriages = new ArrayList<Carriage>();
        this.salary = salary;
        this.isAvailable = true;
    }

    public Employee(
            long id,
            String name,
            List<Qualification> qualifications,
            Company company,
            BigDecimal salary
    ) {
        this.id = id;
        this.name = name;
        this.qualifications = qualifications;
        this.company = company;
        this.carriages = new ArrayList<Carriage>();
        this.salary = salary;
        this.isAvailable = true;
    }

    public Employee(
            long id,
            String name,
            Company company,
            BigDecimal salary
    ) {
        this.id = id;
        this.name = name;
        this.qualifications = new ArrayList<Qualification>();
        this.company = company;
        this.carriages = new ArrayList<Carriage>();
        this.salary = salary;
        this.isAvailable = true;
    }


    // GETTERS
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public Company getCompany() {
        return company;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    // SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


    // FUNCTIONALITY
    public void addCarriage(Carriage carriage) {
        this.carriages.add(carriage);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", salary=" + salary +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
