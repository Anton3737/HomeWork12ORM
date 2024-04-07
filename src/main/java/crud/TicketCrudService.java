package crud;

import connections.ConnectionsToDB;
import dao_intrface.TicketDao;
import entity.Client;
import entity.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketCrudService implements TicketDao {

    private static final String SELECT_TICKET_BY_ID = "SELECT id, created_at, client_id, from_planet_id, to_planet_id FROM ticket WHERE id = ?";
    public static final String INSERT_TICKET = "INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES (?, ?, ?)";
    private static final String UPDATE_TICKET = "UPDATE ticket SET client_id = ?, from_planet_id = ?, to_planet_id = ? WHERE id = ?";
    private static final String DELETE_TICKET = "DELETE FROM Ticket WHERE id = ?";
    private static final String SELECT_ALL_TICKETS = "SELECT * FROM Ticket";


    @Override
    public boolean createTicket(Ticket ticket) {
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, ticket.getClient().getId()); // Встановлюємо id клієнта, який пов'язаний з квитком
            preparedStatement.setString(2, ticket.getFrom_planet_id());
            preparedStatement.setString(3, ticket.getTo_planet_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    ticket.setId(generatedKeys.getInt(1)); // Отримуємо згенерований id для квитка
                }
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Ticket updateTicket(int ticketId, Ticket updatedTicketData) {
        Ticket ticketUpdated  = null;

        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TICKET)) {

            preparedStatement.setInt(4, ticketId); // Встановлюємо id квитка, який потрібно оновити
            preparedStatement.setInt(1, updatedTicketData.getClient().getId());
            preparedStatement.setString(2, updatedTicketData.getFrom_planet_id());
            preparedStatement.setString(3, updatedTicketData.getTo_planet_id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Client ID not found , please revise your ID  ");
            }

            System.out.println("Data are updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketUpdated;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        Ticket ticket = null;
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TICKET_BY_ID)) {

            preparedStatement.setInt(1, ticketId); // Встановлюємо параметр використовуючи ticketId, а не дані з об'єкта ticket
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime localDateTime = resultSet.getTimestamp("created_at").toLocalDateTime();
                int clientId = resultSet.getInt("client_id");
                String fromPlanetId = resultSet.getString("from_planet_id");
                String toPlanetId = resultSet.getString("to_planet_id");

                Client client = new ClientCrudService().getClientById(clientId); // Отримуємо екземпляр клієнта за його id
                ticket = new Ticket(id, client, localDateTime, fromPlanetId, toPlanetId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (ticket == null) {
            throw new RuntimeException("Ticket ID not found ,please revise valid ID");
        }
        return ticket;
    }


    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime localDateTime = resultSet.getTimestamp("created_at").toLocalDateTime();
                int clientId = resultSet.getInt("client_id");
                String fromPlanetId = resultSet.getString("from_planet_id");
                String toPlanetId = resultSet.getString("to_planet_id");

                Client client = new ClientCrudService().getClientById(clientId); // Отримуємо екземпляр клієнта за його id
                tickets.add(new Ticket(id, client, localDateTime, fromPlanetId, toPlanetId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void deleteTicketById(long ticketId) {
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TICKET)) {

            preparedStatement.setLong(1, ticketId);
            preparedStatement.executeUpdate();

            System.out.println("Ticket deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Ticket ID not found for delete data, please revise valid ID");
        }
    }


}
