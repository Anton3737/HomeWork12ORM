package connections;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionsToDB {

    private static ConnectionsToDB INSTANCE = new ConnectionsToDB();

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found.", e);
        }
    }

    private ConnectionsToDB() {

        String url = ConnectionsToDB.getConnectionDB();
        String user = ConnectionsToDB.getUserDB();
        String pass = ConnectionsToDB.getPasswordDB();

        try {
            connection = DriverManager.getConnection(url, user, pass);
//            InitialiseFlyWay.initDBFlyWay();
            System.out.println("Connection to DB is successful");
        } catch (SQLException e) {
            System.out.println(String.format("SQL exception. Can not create connection. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not create connection.");
        }
    }

    public static ConnectionsToDB getINSTANCE() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static String getConnectionDB() {
        try (InputStream inputStream = ConnectionsToDB.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();

            if (inputStream == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            properties.load(inputStream);

            return new StringBuilder("jdbc:postgresql://")
                    .append(properties.getProperty("postgres.db.host"))
                    .append(":")
                    .append(properties.getProperty("postgres.db.port"))
                    .append("/")
                    .append(properties.getProperty("postgres.db.database"))
                    .append("?currentSchema=public")
                    .toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserDB() {
        try (InputStream inputStream = ConnectionsToDB.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();

            if (inputStream == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            properties.load(inputStream);

            return properties.getProperty("postgres.db.username");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static String getPasswordDB() {
        try (InputStream inputStream = ConnectionsToDB.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();

            if (inputStream == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            properties.load(inputStream);

            return properties.getProperty("postgres.db.password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
