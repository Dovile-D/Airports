package DAO;

import entity.Airport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class AirportDAO {
    public static void create(Airport airport) {
        //    Session factory:
//    Reads the hibernate config file
//    Creates Session objects
//    Heavy - weight object
//    Only create once in your app
//    You should have a Session factory to use any CRUD method
        Session session = HibernateUtil.getSessionFactory().openSession();

//      Session:
//      Wraps a JDBC connection
//      Main object used to save/ retrieve objects
//      Short - lived object
//      Retrieved from session Factory
//      You should have separate session for every CRUD method
        Transaction transaction = session.beginTransaction();
        // saugojamas oro uosto objektas:
        session.save(airport); // nereikia rasyti zemo lygio uzklausu kodo kaip JDBC

        // transakcijos ivykdymas (BUTINA):
        transaction.commit();

        // TODO: savarankiskai padaryti redagavima ir trynima, jei liks laiko paziureti video su paieska


    }

    public static void update(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.update(airport);

        session.getTransaction().commit();
    }

    public static void deleteById(int id) {
        Airport airport = searchById(id);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.delete(airport);

        session.getTransaction().commit();
    }

    public static void delete(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.delete(airport);

        session.getTransaction().commit();
    }

    public static Airport searchById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Airport airport = session.get(Airport.class, id);

        session.getTransaction().commit();

        return airport;
    }

    public static List<Airport> searchByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Airport> airports = new ArrayList<>();

        airports = session.createQuery("FROM Airport airport.name LIKE '" + name + "'").getResultList();

        session.getTransaction().commit();

        return airports;
    }

    public static List<Airport> searchForList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Airport> airports = new ArrayList<>();

        airports = session.createQuery("FROM Airport").getResultList();

        session.getTransaction().commit();

        return airports;
    }

}
