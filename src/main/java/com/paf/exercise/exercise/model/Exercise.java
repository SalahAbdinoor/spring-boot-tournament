package com.paf.exercise.exercise.model;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    /* Variables */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "attending_players")
    private Player attendingPlayers;

    /* Constructors  */
    public Exercise(Tournament tournament, Player attendingPlayers) {
        this.tournament = tournament;
        this.attendingPlayers = attendingPlayers;
    }

    public Exercise() {
    }

    /* Getters & Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getAttendingPlayers() {
        return attendingPlayers;
    }

    public void setAttendingPlayers(Player attendingPlayers) {
        this.attendingPlayers = attendingPlayers;
    }

    @Override
    public String toString() {
        return "Exercise{ " +
                "id = " + id +
                ", tournament = " + tournament +
                ", attendingPlayers = " + attendingPlayers +
                '}';
    }
}
