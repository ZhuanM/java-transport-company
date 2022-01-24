package DAO;

import Config.SessionFactoryUtil;
import Entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {
    public static List<Vehicle> allVehicles() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT v FROM Vehicle v"
                    , Vehicle.class
            ).getResultList();
        }
    }

    public static Vehicle getVehicle(long vehicleId) {
        Vehicle vehicle;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            vehicle = session.get(Vehicle.class, vehicleId);

            transaction.commit();
        }

        return vehicle;
    }

    public static void saveVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(vehicle);

            transaction.commit();
        }
    }

    public static void saveOrUpdateVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(vehicle);

            transaction.commit();
        }
    }

    public static void saveVehicles(List<Vehicle> vehicles) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            vehicles.stream().forEach((vehicle) -> session.save(vehicle));

            transaction.commit();
        }
    }

    public static void deleteVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(vehicle);

            transaction.commit();
        }
    }
}
