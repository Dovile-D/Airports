package utils;

import DAO.AirportDAO;
import entity.Airport;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties

                // JDBC Database connection settings
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/airports");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");

                //Select our SQL dialect
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

                // Print SQL to stdout
                settings.put(Environment.SHOW_SQL, "true");

                // Set the current session context
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                // Automatically validates or exports schema to DB when sessionFactory is created
                /*
                    validate: validate the schema, makes no changes to the database.
                    update: update the schema.
                    create: creates the schema, destroying previous data.
                    create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
                    none: does nothing with the schema, makes no changes to the database
                 */
                settings.put(Environment.HBM2DDL_AUTO, "update");   //  Jei irasysime create, kiekviena karta kurs is naujo, sena info pasalins

                configuration.setProperties(settings);

                // Adding annotated class (entity) to configuration
                configuration.addAnnotatedClass(Airport.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
