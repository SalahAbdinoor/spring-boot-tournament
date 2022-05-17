package com.paf.exercise.exercise.model;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.*;

@Entity
@Table(name = "tournament")
public class Tournament extends Audit {

    // allowed currencies as rewards
    private enum currencies {EUR,}

    /* Variables */

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reward_amount", nullable = false)
    private double rewardAmount;

    @Column(name = "currency", nullable = false)
    private String currency;

    /* Constructors */

    public Tournament(String name, double rewardAmount, String currency) throws IllegalArgumentException {
        super();
        this.name = name;
        this.rewardAmount = rewardAmount;
        this.currency = checkCurrencies(currency);

    }

    public Tournament() {
    }

    /* Getters & Setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency if currency is inside enum - currencies
     *
     * @param currency
     * @throws IllegalArgumentException
     */
    public void setCurrency(String currency) throws IllegalArgumentException {
        this.currency = checkCurrencies(currency);
    }

    @Override
    public String toString() {
        return "Tournament{ " +
                "id = " + getId() +
                ", name = '" + name + '\'' +
                ", rewardAmount = " + rewardAmount +
                ", currency = '" + currency + '\'' +
                " }";
    }

    /* Validation */

    /**
     * This helper-method checks to see if input-currency is allowed
     * in reference to "currencies" enum.
     *
     * @param currency input currency from user
     * @return if currency is allowed ? return currency : throw exception
     */
    private String checkCurrencies(String currency) throws IllegalArgumentException {

        // For each currency in enum
        for (currencies allowedCurrencies : currencies.values()) {
            // If currency matches input-currency
            if (allowedCurrencies.name().equals(currency)) {
                return currency;
            }
        }

        // If input-currency wasn't found in enum
        throw new IllegalArgumentException("Currency is not allowed, please try again!");
    }
}
