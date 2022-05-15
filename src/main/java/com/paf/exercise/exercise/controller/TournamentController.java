package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v2/tournament")
public class TournamentController {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * @return list of tournament
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    /**
     * Get tournament by ID.
     *
     * @param id ID of tournament
     * @return Success || Error
     */
    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    String getTournament(@PathVariable Long id) {

        if (!tournamentRepository.existsById(id))
            return "There is no tournament with ID: " + id;
        else
            return tournamentRepository.findById(id).get().toString();
    }

    /**
     * Add new tournament.
     *
     * @param name         Name of tournament
     * @param rewardAmount Reward amount
     * @param currency     Currency of reward-amount
     * @return Success || Error
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    String addTournament(@RequestParam String name,
                         @RequestParam double rewardAmount,
                         @RequestParam String currency) {

        try {
            // If tournament already exist
            if (tournamentRepository.existsByName(name))
                return "Tournament name is already in use;\nPlease try another name!";

                // If parameters don't meet conditions
            else if (name.length() < 4 || rewardAmount < 0 || currency.length() != 3)
                return "Name needs to be more than characters" +
                        "\nReward-amount needs to be more than 0" +
                        "\nCurrency needs to be 3 characters";

                // Successful creation of new player
            else {
                Tournament tournament = new Tournament(name, rewardAmount, currency);
                tournamentRepository.save(tournament);

                return "New tournament added: " + tournament;
            }

            // If currency doesn't exist in currencies - enum - @Tournament.java
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went horribly wrong!";
        }
    }

    /**
     * Change the name, reward-amount and/or currency of existing tournament.
     *
     * @param id              ID of tournament getting updated
     * @param newName         New name of tournament
     * @param newRewardAmount New amount for reward
     * @param newCurrency     New currency of reward-amount
     * @return Success || Error
     */
    @PutMapping(path = "/put/{id}")
    public @ResponseBody
    String updateTournament(@PathVariable Long id,
                            @RequestParam String newName,
                            @RequestParam double newRewardAmount,
                            @RequestParam String newCurrency) {
        try {
            // If ID doesn't exist
            if (!tournamentRepository.existsById(id)) {
                return "There is no tournament with ID: " + id;

                // If tournament-name is taken
            } else if (tournamentRepository.existsByName(newName)) {
                return "Tournament name is already in use;\nPlease try another name!";

            } else {
                // Get tournament
                Tournament tournament = tournamentRepository.findById(id).get();
                String oldName = tournament.getName();
                double oldRewardAmount = tournament.getRewardAmount();
                String oldCurrency = tournament.getCurrency();

                // set new name & reward-amount
                tournament.setName(newName);
                tournament.setRewardAmount(newRewardAmount);
                tournament.setCurrency(newCurrency);

                tournamentRepository.save(tournament);

                // Successful change
                return "Tournament parameters changed: \n" + oldName + " -> " + newName
                        + "\n" + oldRewardAmount + " -> " + newRewardAmount
                        + "\n" + oldCurrency + " -> " + newCurrency;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "something went horribly wrong!";
        }
    }

    /**
     * Deletes tournament based on ID.
     *
     * @param id ID of tournament that is getting deleted
     * @return Success || Error
     */
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteTournament(@PathVariable Long id) {

        if (!tournamentRepository.existsById(id))
            return "There is no tournament with ID: " + id;

        else {
            Tournament tournament = tournamentRepository.findById(id).get();

            // if tournament is connected to exercise.
            // Deletes connected exercise.
            // TODO: Fix with cascade.ALL in Exercise model.
            if (exerciseRepository.existsByTournament(tournament))
                exerciseRepository.delete(exerciseRepository.findByTournament(tournament).get());

            tournamentRepository.delete(tournament);

            return "Tournament: " + tournament.getName() + " has been deleted";
        }
    }
}
