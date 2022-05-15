package com.paf.exercise.exercise.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player extends Audit {

    /* Variables */
    //@Column(name = "name", nullable = false)
    private String name;

    /* Constructors */
    public Player(String name) {
        super();
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

    @Override
    public String toString() {
        return "Player{ " +
                "id = " + getId() +
                ", name = '" + name + '\'' +
                " }";
    }
}
