package dao_intrface;

import entity.Client;
import entity.Planet;

import java.util.List;

public interface PlanetDao {

    boolean createPlanet(Planet planet);

    Planet updatePlanet(Planet planet);

    Planet getPlanetById(String planetId);

    List<Planet> getAllPlanet();

    void deletePlanetById(String planetId);
}
