import crud.ClientCrudService;
import crud.PlanetCrudService;
import crud.TicketCrudService;
import entity.Client;
import entity.Planet;
import entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;

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
//        System.out.println(dao.getAllClients().toString());
//
//
//        PlanetCrudService planetDao = new PlanetCrudService();
//        System.out.println(planetDao.getAllPlanet());
//
//        planetDao.createPlanet(preparePlanet("SUN", "SUN"));
//        System.out.println(planetDao.getPlanetById("SUN"));
//
//        System.out.println(planetDao.getAllPlanet());
//
//        planetDao.updatePlanet(preparePlanet("SUN", "SONECHCO"));
//        System.out.println(planetDao.getPlanetById("SUN"));
//        System.out.println(planetDao.getAllPlanet());
//
//        planetDao.deletePlanetById("SUN");
//        System.out.println(planetDao.getAllPlanet());
//
////        planetDao.createPlanet(preparePlanet("PLUTO","PLUTON - one love"));
//        System.out.println(planetDao.getAllPlanet());


        ClientCrudService clientCrudService = new ClientCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();


//        Ticket ticketCrudService1 = new TicketCrudService().getTicketById(12);
//        System.out.println(ticketCrudService1);


//        clientCrudService.createClient(client);
        System.out.println(clientCrudService.getClientById(12));


//        ticketCrudService.createTicket(new Ticket(clientCrudService.getClientById(12), "PLUTO", "VENUS"));

//        ticketCrudService.updateTicket(34, new Ticket(clientCrudService.getClientById(12), "VENUS", "EARTH"));



//        ticketCrudService.deleteTicketById(25);
//        ticketCrudService.deleteTicketById(26);

        List<Ticket> ticketCrudServicelist = new TicketCrudService().getAllTickets();
        for (Ticket ticket : ticketCrudServicelist) {
            System.out.println(ticket);
        }


//        ticketCrudService.createTicket(new Ticket(12, "PLUTO", "VENUS"));

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
