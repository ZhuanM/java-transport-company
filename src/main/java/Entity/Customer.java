package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany()
    private List<Company> companies;

    @OneToMany(mappedBy = "customer")
    private List<Carriage> carriages;

    // CONSTRUCTORS
    public Customer() { }

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
        this.companies = new ArrayList<Company>();
        this.carriages = new ArrayList<Carriage>();
    }

    public Customer(
            long id,
            String name,
            List<Company> companies,
            List<Carriage> carriages
    ) {
        this.id = id;
        this.name = name;
        this.companies = companies;
        this.carriages = carriages;
    }

    public void addCarriage(Carriage carriage) {
        this.carriages.add(carriage);
    }

    public void addCompany(Company company) {
        this.companies.add(company);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
