package DAO;

import Config.SessionFactoryUtil;
import Entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static List<Employee> allEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT e FROM Employee e"
                    , Employee.class
            ).getResultList();
        }
    }

    public static Employee getEmployee(long employeeId) {
        Employee employee;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employee = session.get(Employee.class, employeeId);

            transaction.commit();
        }

        return employee;
    }

    public static void saveEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(employee);

            transaction.commit();
        }
    }

    public static void saveOrUpdateEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(employee);

            transaction.commit();
        }
    }

    public static void saveEmployees(List<Employee> employees) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employees.stream().forEach((employee) -> session.save(employee));

            transaction.commit();
        }
    }

    public static void deleteEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(employee);

            transaction.commit();
        }
    }

    public static List<Employee> employeesSortedByQualificationAndSalary() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> root = cq.from(Employee.class);
            cq.select(root);

            ArrayList<Order> orders = new ArrayList<>();
            orders.add(cb.asc(root.get("qualification")));
            orders.add(cb.desc(root.get("salary")));
            cq.orderBy(orders);

            Query<Employee> query = session.createQuery(cq);
            ArrayList<Employee> employees = (ArrayList<Employee>) query.getResultList();

            return employees;
        }
    }
}
