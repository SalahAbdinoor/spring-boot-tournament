package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Controller
@RequestMapping(path = "/api/v2/tournament")
public class TournamentController {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * @return list of tournament
     */
    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    /**
     * Get tournament by ID.
     *
     * @param id ID of tournament
     * @return ResponseEntity -> Success || Error
     */
    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    ResponseEntity<String> getTournament(@PathVariable Long id) {

        if (!tournamentRepository.existsById(id))
            return new ResponseEntity<>("There is no tournament with ID: " + id, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(tournamentRepository.findById(id).get().toString(), HttpStatus.OK);
    }

    /**
     * Add new tournament.
     *
     * @param name         Name of tournament
     * @param rewardAmount Reward amount
     * @param currency     Currency of reward-amount
     * @return ResponseEntity -> Success || Error
     */
    @PostMapping(path = "/add")
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public @ResponseBody
    ResponseEntity<String> addTournament(@RequestParam String name,
                                         @RequestParam Double rewardAmount,
                                         @RequestParam String currency) throws NumberFormatException {

        try {
            // If tournament already exist
            if (tournamentRepository.existsByName(name))
                return new ResponseEntity<>("Tournament name is already in use;\nPlease try another name!", HttpStatus.FORBIDDEN);

                // If parameters don't meet conditions
            else if (name.length() < 4 || rewardAmount < 0 || currency.length() != 3)
                return new ResponseEntity<>("Name needs to be more than characters" +
                        "\nReward-amount needs to be more than 0" +
                        "\nCurrency needs to be 3 characters", HttpStatus.LENGTH_REQUIRED);

                // Successful creation of new player
            else {
                Tournament tournament = new Tournament(name, rewardAmount, currency);
                tournamentRepository.save(tournament);

                return new ResponseEntity<>("New tournament added: " + tournament, HttpStatus.CREATED);
            }
            // If currency doesn't exist in currencies - enum - @Tournament.java
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Reward amount must be a number!", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Change the name, reward-amount and/or currency of existing tournament.
     *
     * @param id              ID of tournament getting updated
     * @param newName         New name of tournament
     * @param newRewardAmount New amount for reward
     * @param newCurrency     New currency of reward-amount
     * @return ResponseEntity -> Success || Error
     */
    @PutMapping(path = "/put/{id}")
    public @ResponseBody
    ResponseEntity<String> updateTournament(@PathVariable Long id,
                                            @RequestParam String newName,
                                            @RequestParam double newRewardAmount,
                                            @RequestParam String newCurrency) {
        try {
            // If ID doesn't exist
            if (!tournamentRepository.existsById(id)) {
                return new ResponseEntity<>("There is no tournament with ID: " + id, HttpStatus.NOT_FOUND);

                // If tournament-name is taken
            } else if (tournamentRepository.existsByName(newName)) {
                return new ResponseEntity<>("name is already in use;\nPlease try another name!", HttpStatus.FORBIDDEN);

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

                return new ResponseEntity<>("Tournament parameters changed: \n" + oldName + " -> " + newName
                        + "\n" + oldRewardAmount + " -> " + newRewardAmount
                        + "\n" + oldCurrency + " -> " + newCurrency, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes tournament based on ID.
     *
     * @param id ID of tournament that is getting deleted
     * @return ResponseEntity -> Success || Error
     */
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteTournament(@PathVariable Long id) {

        if (!tournamentRepository.existsById(id))
            return new ResponseEntity<>("There is no tournament with ID: " + id, HttpStatus.NOT_FOUND);

        else {
            Tournament tournament = tournamentRepository.findById(id).get();

            // if tournament is connected to exercise.
            // Deletes connected exercise.
            // TODO: Fix with cascade.ALL in Exercise model.
            if (exerciseRepository.existsByTournament(tournament))
                exerciseRepository.delete(exerciseRepository.findByTournament(tournament).get());

            tournamentRepository.delete(tournament);

            return new ResponseEntity<>("Tournament: " + tournament.getName() + " has been deleted", HttpStatus.OK);
        }
    }
}
