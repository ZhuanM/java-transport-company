package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "qualification")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QualificationType type;

    @ManyToMany
    private List<Employee> employees;

    // CONSTRUCTORS
    public Qualification() {
        this.employees = new ArrayList<Employee>();
    }

    public Qualification(QualificationType type) {
        this.type = type;
        this.employees = new ArrayList<Employee>();
    }

    public Qualification(QualificationType type, List<Employee> employees) {
        this.type = type;
        this.employees = employees;
    }

    // GETTERS
    public long getId() {
        return id;
    }

    public QualificationType getType() {
        return type;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    // SETTERS

    public void setId(long id) {
        this.id = id;
    }

    public void setType(QualificationType type) {
        this.type = type;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
