package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column
    private LocalDateTime localDateTime;


    @Column
    private String from_planet_id;

    @Column
    private String to_planet_id;


    public Ticket(int id, Client client, LocalDateTime localDateTime, String from_planet_id, String to_planet_id) {
        this.id = id;
        this.client = client;
        this.localDateTime = localDateTime;
        this.from_planet_id = from_planet_id;
        this.to_planet_id = to_planet_id;
    }

    public Ticket(Client client, String from_planet_id, String to_planet_id) {
        this.client = client;
        this.from_planet_id = from_planet_id;
        this.to_planet_id = to_planet_id;
    }

    @Override
    public String toString() {
        String clientInfo = client != null ? "( Клієнт ID " + client.getId() + " | Клієнт Name " + client.getName() + " ) " : "( Клієнт не визначено )";
        return "Номер квитка № = " + id + " | Квиток куплено " + localDateTime + " | " + clientInfo + " | Виліт з космодрому планети = " + from_planet_id + " | Приліт на космодром планети = " + to_planet_id + "\n";
    }

}
