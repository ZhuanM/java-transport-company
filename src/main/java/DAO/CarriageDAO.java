package DAO;

import Config.SessionFactoryUtil;
import Entity.Carriage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarriageDAO {
    public static List<Carriage> allCarriages() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT c FROM Carriages c"
                    , Carriage.class
            ).getResultList();
        }
    }

    public static Carriage getCarriage(long carriageId) {
        Carriage carriage;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            carriage = session.get(Carriage.class, carriageId);

            transaction.commit();
        }

        return carriage;
    }

    public static void saveCarriage(Carriage carriage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(carriage);

            transaction.commit();
        }
    }

    public static void saveOrUpdateCarriage(Carriage carriage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(carriage);

            transaction.commit();
        }
    }

    public static void saveCarriages(List<Carriage> carriages) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            carriages.stream().forEach((carr) -> session.save(carr));

            transaction.commit();
        }
    }

    public static void deleteCarriage(Carriage carriage) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(carriage);

            transaction.commit();
        }
    }

    public static List<Carriage> carriagesSortedByDestination() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT carr FROM Carriage carr ORDER BY to_destination ASC"
                    , Carriage.class
            ).getResultList();
        }
    }
}
