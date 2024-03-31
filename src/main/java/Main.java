import crud.ClientCrudService;
import crud.PlanetCrudService;
import entity.Client;
import entity.Planet;

public class Main {
    public static void main(String[] args) {

        ClientCrudService dao = new ClientCrudService();
//        dao.createClient(prepareClient(8, "Anton"));
//
//        System.out.println(dao.getClientById(14));
//
//        System.out.println(dao.getAllClients().toString());
//
//        dao.deleteClientById(8l);
//        System.out.println(dao.getAllClients().toString());
//
//        dao.updateClient(prepareClient(9, "Palageia"));
//        System.out.println(dao.getClientById(9));
//        System.out.println(dao.getAllClients().toString());
//
//        dao.deleteClientById(12l);
        System.out.println(dao.getAllClients().toString());


        PlanetCrudService planetDao = new PlanetCrudService();
        System.out.println(planetDao.getAllPlanet());

        planetDao.createPlanet(preparePlanet("SUN", "SUN"));
        System.out.println(planetDao.getPlanetById("SUN"));

        System.out.println(planetDao.getAllPlanet());

        planetDao.updatePlanet(preparePlanet("SUN", "SONECHCO"));
        System.out.println(planetDao.getPlanetById("SUN"));
        System.out.println(planetDao.getAllPlanet());

        planetDao.deletePlanetById("SUN");
        System.out.println(planetDao.getAllPlanet());

//        planetDao.createPlanet(preparePlanet("PLUTO","PLUTON - one love"));
        System.out.println(planetDao.getAllPlanet());


    }

    private static Client prepareClient(int id, String name) {
        Client client = new Client(id, name);
        return client;
    }


    private static Planet preparePlanet(String id, String name) {
        Planet planet = new Planet(id, name);
        return planet;
    }
}
