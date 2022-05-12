package com.paf.exercise.exercise.model;

import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player {

    /* Variables */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /* Constructors */
    public Player(String name) {
        //super();
        this.name = name;
    }

    public Player() {
    }

    /* Getters & Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Player{ " +
                "id = " + id +
                ", name = '" + name + '\'' +
                " }";
    }
}
