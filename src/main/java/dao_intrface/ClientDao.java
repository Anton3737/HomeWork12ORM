package dao_intrface;

import entity.Client;

import java.util.List;

public interface ClientDao {

    boolean createClient(Client client);

    Client updateClient(Client client);

    Client getClientById(int clientId);

    List<Client> getAllClients();

    void deleteClientById(Long clientId);


}
