package Entity;

import javax.persistence.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "carriage")
public class Carriage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CarriageType type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "from_destination", nullable = false)
    private String fromDestination;

    @Column(name = "to_destination", nullable = false)
    private String toDestination;

    @Column(name = "sent_date", nullable = false)
    private LocalDate sentDate;

    @Column(name = "received_date", nullable = false)
    private LocalDate receivedDate;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "isPaid", nullable = false)
    private boolean isPaid;


    // CONSTRUCTORS
    public Carriage() { }

    public Carriage(
            long id,
            CarriageType type,
            Company company,
            Customer customer,
            Employee employee,
            String weight,
            String fromDestination,
            String toDestination,
            LocalDate sentDate,
            LocalDate receivedDate,
            BigDecimal price,
            boolean isPaid
    ) {
        this.id = id;
        this.type = type;
        this.company = company;
//        this.setEmployee(employee);
        this.customer = customer;
        this.weight = weight;
        this.fromDestination = fromDestination;
        this.toDestination = toDestination;
        this.sentDate = sentDate;
        this.receivedDate = receivedDate;
        this.price = price;
        this.isPaid = isPaid;
    }


    // GETTERS
    public long getId() {
        return id;
    }

    public CarriageType getType() {
        return type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Company getCompany() {
        return company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getWeight() {
        return weight;
    }

    public String getFromDestination() {
        return fromDestination;
    }

    public String getToDestination() {
        return toDestination;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean getIsPaid() {
        return isPaid;
    }


    // SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setType(CarriageType type) {
        this.type = type;
    }

//    public void setEmployee(Employee employee) {
//        if (this.employee.isAvailable()) {
//            this.employee = employee;
//        } else {
//            System.out.println("Employee is unavailable for this carriage!");
//        }
//    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setFromDestination(String fromDestination) {
        this.fromDestination = fromDestination;
    }

    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }


    // FILES
    public void serialize() {
        String fileName = this.getId() + ".ser";
        File filePath = new File("./Carriages/" + fileName);

        try (
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(this);
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void deserialize() {
        String fileName = this.getId() + ".txt";

        try(
                FileWriter fw = new FileWriter("./Carriages/" + fileName, false)
        ) {
            fw.write(this.toString());
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public String toString() {
        return "Carriage{" +
                "id=" + id +
                ", type=" + type +
                ", employee=" + employee +
                ", company=" + company +
                ", customer=" + customer +
                ", weight='" + weight + '\'' +
                ", fromDestination='" + fromDestination + '\'' +
                ", toDestination='" + toDestination + '\'' +
                ", sentDate=" + sentDate +
                ", receivedDate=" + receivedDate +
                ", price=" + price +
                ", isPaid=" + isPaid +
                '}';
    }
}
