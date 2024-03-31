package crud;

import connections.ConnectionsToDB;
import dao_intrface.ClientDao;
import entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private static final String SELECT_CLIENT = "SELECT id , name FROM client WHERE id = ?";
    public static final String INSERT_CLIENT = "INSERT INTO client (NAME) VALUES (?)";
    private static final String UPDATE_CLIENT = "UPDATE client SET NAME = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM client";


    @Override
    public boolean createClient(Client client) {

        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
            preparedStatement = connection.prepareStatement(INSERT_CLIENT);
            preparedStatement.setString(1, client.getName()); // Встановлюємо значення параметру для поля name

            int rowsAffected = preparedStatement.executeUpdate(); // Виконуємо SQL-запит і отримуємо кількість змінених рядків

            return rowsAffected > 0; // Повертаємо true, якщо хоча б один рядок був змінений, тобто якщо операція була успішною

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client updateClient(Client client) {
        Client clientObj = null;
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT)) {

            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Client ID not found , please revise your ID  ");
            }

            System.out.println("Data are updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientObj;
    }

    public Client getClientById(int clientId) {
        Client client = null;

        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT)) {

            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                client = new Client(Id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (client == null) {
            throw new RuntimeException("Client ID not found ,please revise valid ID");
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clientsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                clientsList.add(new Client(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsList;
    }

    @Override
    public void deleteClientById(Long clientId) {
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT)) {

            preparedStatement.setLong(1, clientId);
            preparedStatement.executeUpdate();

            System.out.println("Client deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Client ID not found for delete data, please revise valid ID");
        }
    }
}
