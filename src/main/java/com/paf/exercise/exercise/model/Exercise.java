package com.paf.exercise.exercise.model;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise extends Audit {

    /* Variables */

    @OneToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    /* Constructors  */
    public Exercise(Tournament tournament, Player player) {
        super();
        this.tournament = tournament;
        this.player = player;
    }

    public Exercise() {
    }

    /* Getters & Setters */

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Exercise{ " +
                "id = " + getId() +
                ", tournament = " + tournament +
                ", player = " + player +
                '}';
    }
}
