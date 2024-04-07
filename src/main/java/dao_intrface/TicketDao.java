package dao_intrface;

import entity.Ticket;

import java.util.List;

public interface TicketDao {


    boolean createTicket(Ticket ticket);


    Ticket updateTicket(int ticketId, Ticket updatedTicketData);

    Ticket getTicketById(int ticketId);

    List<Ticket> getAllTickets();

    void deleteTicketById(long ticketId);


}
