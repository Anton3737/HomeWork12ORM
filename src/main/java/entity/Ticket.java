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

    @Column
    private LocalDateTime localDateTime;

    @Column
    private int client_id;

    @Column
    private int from_planet_id;

    @Column
    private int to_planet_id;


}
