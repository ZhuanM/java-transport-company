import DAO.*;
import Entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company1 = new Company(1, "company1");
        Company company2 = new Company(2, "company2");
        Company company3 = new Company(3, "company3");

        company1.setProfit(BigDecimal.valueOf(10000));
        company2.setProfit(BigDecimal.valueOf(350));
        company3.setProfit(BigDecimal.valueOf(750));

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);

        CompanyDAO.saveCompanies(companies);

//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        Vehicle vehicle1 = new Vehicle(1, "vehicle1", VehicleType.TRUCK, company1);
        Vehicle vehicle2 = new Vehicle(2, "vehicle2", VehicleType.BUS, company1);
        Vehicle vehicle3 = new Vehicle(3, "vehicle3", VehicleType.CISTERN_TRUCK, company2);
        Vehicle vehicle4 = new Vehicle(4, "vehicle4", VehicleType.BUS, company3);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);

        VehicleDAO.saveVehicles(vehicles);

//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        Qualification qualification1 = new Qualification(QualificationType.NORMAL);
        Qualification qualification2 = new Qualification(QualificationType.PEOPLE);
        Qualification qualification3 = new Qualification(QualificationType.SPECIAL);

        List<Qualification> qualifications = new ArrayList<>();
        qualifications.add(qualification1);
        qualifications.add(qualification2);
        qualifications.add(qualification3);

        QualificationDAO.saveQualifications(qualifications);

//        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        List<Qualification> employee1Qualifications = new ArrayList<>();
        employee1Qualifications.add(qualification2);
        employee1Qualifications.add(qualification3);
        Employee employee1 = new Employee(1, "Nikola", employee1Qualifications, company1, BigDecimal.valueOf(700));

        List<Qualification> employee2Qualifications = new ArrayList<>();
        employee2Qualifications.add(qualification1);
        employee2Qualifications.add(qualification2);
        Employee employee2 = new Employee(2, "Pavel", employee2Qualifications, company2, BigDecimal.valueOf(1250));

        List<Qualification> employee3Qualifications = new ArrayList<>();
        employee3Qualifications.add(qualification3);
        Employee employee3 = new Employee(3, "Georgi", employee3Qualifications, company2, BigDecimal.valueOf(1300));

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        EmployeeDAO.saveEmployees(employees);

        //        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        Customer customer1 = new Customer(1, "Ivan");

        Carriage carriage1 = new Carriage(1, CarriageType.NORMAL, company1, customer1, employee2, "235", "Burgas", "Sofia",
                LocalDate.of(2022, 1, 4), LocalDate.of(2022, 1, 8), BigDecimal.valueOf(360), true);

        Carriage carriage2 = new Carriage(2, CarriageType.SPECIAL, company1, customer1, employee3, "60", "Plovdiv", "Blagoevgrad",
                LocalDate.of(2021, 12, 16), LocalDate.of(2021, 12, 21), BigDecimal.valueOf(45), true);

        Carriage carriage3 = new Carriage(3, CarriageType.NORMAL, company1, customer1, employee2, "235", "Sofia", "Varna",
                LocalDate.of(2021, 11, 17), LocalDate.of(2021, 11, 24), BigDecimal.valueOf(80), true);

        CustomerDAO.saveCustomer(customer1);

        EmployeeDAO.saveEmployee(employee1);
        EmployeeDAO.saveEmployee(employee2);
        EmployeeDAO.saveEmployee(employee3);

        List<Carriage> carriages = new ArrayList<>();
        carriages.add(carriage1);
        carriages.add(carriage2);
        carriages.add(carriage3);
        CarriageDAO.saveCarriages(carriages);

        // DAO All Employees
        List<Employee> employeesDAO = EmployeeDAO.allEmployees();
        employeesDAO.stream().forEach(System.out::println);

        // DAO Carriages Sorted By Destination
        List<Carriage> carriagesSortedDAO = CarriageDAO.carriagesSortedByDestination();
        carriagesSortedDAO.stream().forEach(System.out::println);

        // Serializing
        List<Carriage> serializeCarriages = new ArrayList<>();
        serializeCarriages.add(carriage1);
        serializeCarriages.add(carriage2);
        serializeCarriages.add(carriage3);

        serializeCarriages.forEach(Carriage::serialize);
        serializeCarriages.forEach(Carriage::deserialize);
    }
}
