package connections;

import entity.Client;
import entity.Planet;
import entity.Ticket;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static HibernateUtils HibINSTANCE = new HibernateUtils();

    private SessionFactory sessionFactory;

    private HibernateUtils() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();

        flywayMigration(PropertyReader.getConnectionUrlForPostgres(),
                PropertyReader.getUserForPostgres(),
                PropertyReader.getPasswordForPostgres());
    }


    public static HibernateUtils getHibINSTANCE() {
        return HibINSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public void closeSessionFactory() {
        this.sessionFactory.close();
    }


    private void flywayMigration(String connectionUrl, String username, String password) {
        Flyway flyway = Flyway.configure().dataSource(connectionUrl, username, password).load();
        flyway.migrate();
    }
}
