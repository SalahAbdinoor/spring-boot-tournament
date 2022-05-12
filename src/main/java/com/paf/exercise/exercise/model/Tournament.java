package com.paf.exercise.exercise.model;

import javax.persistence.*;

@Entity
@Table(name = "tournament")
public class Tournament {

    /* Variables */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reward_amount", nullable = false)
    private double rewardAmount;

    @Column(name = "currency", nullable = false)
    private String currency;

    public Tournament(String name, double rewardAmount, String currency) {
        this.name = name;
        this.rewardAmount = rewardAmount;
        this.currency = currency;
    }

    // Currently, allowed currencies as rewards
    public enum currencies { EUR, }

    /* Constructors */

    public Tournament() {
    }

    /* Getters & Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tournament{ " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", rewardAmount = " + rewardAmount +
                " }";
    }
}
