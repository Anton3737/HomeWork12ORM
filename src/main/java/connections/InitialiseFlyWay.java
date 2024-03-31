package connections;

import org.flywaydb.core.Flyway;

public class InitialiseFlyWay {

    public static void initDBFlyWay() {
        String url = ConnectionsToDB.getConnectionDB();
        String user = ConnectionsToDB.getUserDB();
        String pass = ConnectionsToDB.getPasswordDB();

        Flyway flyway = Flyway.configure().dataSource(url, user, pass).load();
        flyway.migrate();
    }

}
