package entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "planet")
@Data
public class Planet {

    @Id
    private String id;

    @Column
    private String name;


    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
