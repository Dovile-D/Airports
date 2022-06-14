package utils;

import entity.Airport;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.lang.module.Configuration;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory() == 0) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/airports");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");



                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
                // rodo kokias hibernate atlieka uzklausas:
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Airport.class);


                ServiceRegistry serviceRegistry = new StandartServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                return sessionFactory;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}
