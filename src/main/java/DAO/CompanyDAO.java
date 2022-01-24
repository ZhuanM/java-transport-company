package DAO;

import Config.SessionFactoryUtil;
import Entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    public static List<Company> allCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT com FROM Company com"
                    , Company.class
            ).getResultList();
        }
    }

    public static Company getCompany(long companyId) {
        Company company;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            company = session.get(Company.class, companyId);

            transaction.commit();
        }

        return company;
    }

    public static void saveCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(company);

            transaction.commit();
        }
    }

    public static void saveOrUpdateCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(company);

            transaction.commit();
        }
    }

    public static void saveCompanies(List<Company> companies) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            companies.stream().forEach((com) -> session.save(com));

            transaction.commit();
        }
    }

    public static void deleteCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(company);

            transaction.commit();
        }
    }

    public static List<Company> companiesSortedByNameAndProfit() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cq = cb.createQuery(Company.class);
            Root<Company> root = cq.from(Company.class);
            cq.select(root);

            ArrayList<Order> orders = new ArrayList<>();
            orders.add(cb.asc(root.get("name")));
            orders.add(cb.desc(root.get("profit")));
            cq.orderBy(orders);

            Query<Company> query = session.createQuery(cq);
            ArrayList<Company> companies = (ArrayList<Company>) query.getResultList();

            return companies;
        }
    }
}
