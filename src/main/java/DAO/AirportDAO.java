package DAO;

import entity.Airport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class AirportDAO {
    public static void create(Airport airport){

        // bet kokiam CRUD veiksmui atlikti reikalinga nauja sesija. per aplikacijos veikima viena gamykla
        // (getSessionFactory) ir daug sesiju

        Session session = HibernateUtil.getSessionFactory().openSession();

        // kai gauname sesija, galim pradeti transakcija
        // bet kokiam CRUD veiksmui atlikti butina transakcija (ivykdymas)
        Transaction transaction = session.beginTransaction();
        // saugojamas oro uosto objektas:
        session.save(airport); // nereikia rasyti zemo lygio uzklausu kodo kaip JDBC

        // transakcijos ivykdymas (BUTINA):
        transaction.commit();

        // TODO: savarankiskai padaryti redagavima ir trynima, jei liks laiko paziureti video su paieska


    }
}
