package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/v2/exercise")
public class ExerciseController {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * @return list of exercises
     */
    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<Exercise> getExercises() {

        return exerciseRepository.findAll();
    }

    /**
     * Get exercise by ID.
     *
     * @param id ID of exercise
     * @return ResponseEntity -> Success || Error
     */
    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    ResponseEntity<String> getExercise(@PathVariable Long id) {

        if (!exerciseRepository.existsById(id))
            return new ResponseEntity<>("There is no exercise with ID: " + id, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(exerciseRepository.findById(id).get().toString(), HttpStatus.OK);
    }

    /**
     * Add new exercise.
     *
     * @param playerId     ID of player that's attending the exercise
     * @param tournamentId ID of tournament that's being exercised
     * @return ResponseEntity -> Success || Error
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity<String> addExercise(@RequestParam Long playerId, @RequestParam Long tournamentId) {

        if (!tournamentRepository.existsById(tournamentId)) {
            return new ResponseEntity<>("Tournament doesn't exist", HttpStatus.NOT_FOUND);

        } else if (!playerRepository.existsById(playerId)) {
            return new ResponseEntity<>("Player doesn't exist", HttpStatus.NOT_FOUND);

            // TODO: add else if("SELECT * FROM EXERCISE WHERE PLAYER_ID=playerId && Tournament_ID=tournamentId")
            //  return "Player is already connected to this tournament!"
        } else {

            Tournament tournament = tournamentRepository.findById(tournamentId).get();
            Player player = playerRepository.findById(playerId).get();

            Exercise exercise = new Exercise(tournament, player);
            exerciseRepository.save(exercise);

            return new ResponseEntity<>("New exercise has been added: \n" + exercise, HttpStatus.CREATED);
        }
    }

    /**
     * Change player and/or tournament of existing exercise.
     *
     * @param id              ID of exercise getting updated
     * @param newPlayerId     ID of new player wanting to attend the exercise
     * @param newTournamentId ID of new tournament that's getting exercised
     * @return ResponseEntity -> Success || Error
     */
    @PutMapping(path = "/put/{id}")
    public @ResponseBody
    ResponseEntity<String> putExercise(@PathVariable Long id,
                                       @RequestParam Long newPlayerId,
                                       @RequestParam Long newTournamentId) {

        if (!exerciseRepository.existsById(id))
            return new ResponseEntity<>("There is no exercise with ID: " + id, HttpStatus.NOT_FOUND);

        else {

            // get exercise by id
            Exercise exercise = exerciseRepository.findById(id).get();

            // old player & tournament
            Player oldPlayer = exercise.getPlayer();
            Tournament oldTournament = exercise.getTournament();

            // new player & tournament
            Player newPlayer = playerRepository.findById(newPlayerId).get();
            Tournament newTournament = tournamentRepository.findById(newTournamentId).get();

            // checks if new player/tournament is the same as the old one
            boolean isNewPlayerSameAsOldPlayer = oldPlayer.equals(newPlayer);
            boolean isNewTournamentSameAsOldTournament = oldTournament.equals(newTournament);

            // if both new player & tournament are the same
            if (isNewPlayerSameAsOldPlayer && isNewTournamentSameAsOldTournament)
                return new ResponseEntity<>("Player is already connected to this tournament!", HttpStatus.FORBIDDEN);

            else {
                // set new player & tournament
                exercise.setPlayer(newPlayer);
                exercise.setTournament(newTournament);

                // save to repository
                exerciseRepository.save(exercise);

                // Successful change
                return new ResponseEntity<>("Exercise parameters changed: \n" + oldPlayer.getName() + " -> " + newPlayer.getName()
                        + "\n" + oldTournament.getName() + " -> " + newTournament.getName(), HttpStatus.OK);
            }
        }
    }


    /**
     * Deletes exercise based on ID.
     *
     * @param id ID of exercise that is getting deleted
     * @return ResponseEntity -> Success || Error
     */
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteExercise(@PathVariable Long id) {

        if (!exerciseRepository.existsById(id))
            return new ResponseEntity<>("There is no exercise with ID: " + id, HttpStatus.NOT_FOUND);

        else {
            Exercise exercise = exerciseRepository.findById(id).get();
            exerciseRepository.delete(exercise);

            return new ResponseEntity<>(exercise + "\nhas been deleted", HttpStatus.OK);
        }
    }
}
