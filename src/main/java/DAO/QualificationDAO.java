package DAO;

import Config.SessionFactoryUtil;
import Entity.Qualification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class QualificationDAO {
    public static List<Qualification> allQualifications() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT q FROM Qualification q"
                    , Qualification.class
            ).getResultList();
        }
    }

    public static Qualification getQualification(long qualificationId) {
        Qualification qualification;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            qualification = session.get(Qualification.class, qualificationId);

            transaction.commit();
        }

        return qualification;
    }

    public static void saveQualification(Qualification qualification) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(qualification);

            transaction.commit();
        }
    }

    public static void saveOrUpdateQualification(Qualification qualification) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(qualification);

            transaction.commit();
        }
    }

    public static void saveQualifications(List<Qualification> qualifications) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            qualifications.stream().forEach((qualification) -> session.save(qualification));

            transaction.commit();
        }
    }

    public static void deleteQualification(Qualification qualification) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(qualification);

            transaction.commit();
        }
    }
}
