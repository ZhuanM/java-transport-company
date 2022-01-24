package Entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // CONSTRUCTORS
    public Vehicle() { }

    public Vehicle(long id, String name, VehicleType type, Company company) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
    }

    // GETTERS
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VehicleType getType() {
        return type;
    }

    public Company getCompany() {
        return company;
    }

    // SETTERS

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", company=" + company +
                '}';
    }
}
