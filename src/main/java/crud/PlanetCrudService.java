package crud;

import connections.ConnectionsToDB;
import dao_intrface.PlanetDao;
import entity.Planet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetCrudService implements PlanetDao {

    private static final String SELECT_PLANET = "SELECT id , name FROM planet WHERE id = ?";
    private static final String INSERT_PLANET = "INSERT INTO Planet (id, name) VALUES (?, ?)";
    private static final String UPDATE_PLANET = "UPDATE planet SET NAME = ? WHERE id = ?";
    private static final String DELETE_PLANET = "DELETE FROM planet WHERE id = ?";
    private static final String SELECT_ALL_PLANET = "SELECT * FROM planet";

    @Override
    public boolean createPlanet(Planet planet) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
            preparedStatement = connection.prepareStatement(INSERT_PLANET);


            preparedStatement.setString(1, planet.getId());
            preparedStatement.setString(2, planet.getName()); // Встановлюємо значення параметру для поля name

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Planet updatePlanet(Planet planet) {
        Planet planetObj = null;
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLANET)) {

            preparedStatement.setString(1, planet.getName());
            preparedStatement.setString(2, planet.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Client ID not found , please revise your ID  ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Client ID not found for update data, please revise valid ID");
        }
        return planetObj;
    }

    @Override
    public Planet getPlanetById(String planetId) {
        Planet planet = null;

        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLANET)) {

            preparedStatement.setString(1, planetId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String Id = resultSet.getString("id");
                String name = resultSet.getString("name");
                planet = new Planet(Id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (planet == null) {
            throw new RuntimeException("Client ID not found ,please revise valid ID");
        }
        return planet;
    }

    @Override
    public List<Planet> getAllPlanet() {
        List<Planet> planetList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PLANET)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                planetList.add(new Planet(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planetList;
    }

    @Override
    public void deletePlanetById(String planetId) {
        try (Connection connection = DriverManager.getConnection(ConnectionsToDB.getConnectionDB(), ConnectionsToDB.getUserDB(), ConnectionsToDB.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLANET)) {

            preparedStatement.setString(1, planetId);
            preparedStatement.executeUpdate();

            System.out.println("Client deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Client ID not found for delete data, please revise valid ID");
        }
    }
}
