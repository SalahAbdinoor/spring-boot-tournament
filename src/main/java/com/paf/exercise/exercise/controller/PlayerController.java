package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/v2/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * @return list of players
     */
    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Get player by ID.
     *
     * @param id ID of player
     * @return ResponseEntity -> Success || Error
     */
    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    ResponseEntity<String> getPlayer(@PathVariable Long id) {

        if (!playerRepository.existsById(id))
            return new ResponseEntity<>("There is no player with ID: " + id, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(playerRepository.findById(id).get().toString(), HttpStatus.OK);
    }

    /**
     * Add new player.
     *
     * @param name Name of player
     * @return ResponseEntity -> Success || Error
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity<String> addPlayer(@RequestParam String name) {

        // If player already exist
        if (playerRepository.existsByName(name))
            return new ResponseEntity<>("Player already exists\nPlease try another name!", HttpStatus.FORBIDDEN);
            // If player name is fewer than 4 characters
        else if (name.length() < 4)
            return new ResponseEntity<>("name must longer than 4 characters", HttpStatus.LENGTH_REQUIRED);

            // Successful creation of new player
        else {
            Player player = new Player(name);
            playerRepository.save(player);

            return new ResponseEntity<>("New player added: " + player, HttpStatus.CREATED);
        }
    }

    /**
     * Change the name of existing player.
     *
     * @param id      ID of player getting updated
     * @param newName New name of player
     * @return ResponseEntity -> Success || Error
     */
    @PutMapping(path = "/put/{id}")
    public @ResponseBody
    ResponseEntity<String> putPlayer(@PathVariable Long id, @RequestParam String newName) {

        // If player doesn't exist
        if (!playerRepository.existsById(id))
            return new ResponseEntity<>("There is no player with ID: " + id, HttpStatus.NOT_FOUND);

            // If player-name is taken
        else if (playerRepository.existsByName(newName))
            return new ResponseEntity<>("Name is already in use;\nPlease try another name!", HttpStatus.FORBIDDEN);

            // If name is fewer than 4 characters
        else if (newName.length() < 4)
            return new ResponseEntity<>("Name must longer than 4 characters", HttpStatus.LENGTH_REQUIRED);

            // Successful name-change
        else {

            // Get player
            Player player = playerRepository.findById(id).get();
            String oldName = player.getName();

            // Set new-name and save to repository
            player.setName(newName);
            playerRepository.save(player);

            return new ResponseEntity<>("Player name changed: " + oldName + " -> " + newName, HttpStatus.OK);
        }
    }

    /**
     * Deletes player based on ID.
     *
     * @param id ID of player that is getting deleted
     * @return ResponseEntity -> Success || Error
     */
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<String> deletePlayer(@PathVariable Long id) {

        // If player doesn't exist
        if (!playerRepository.existsById(id))
            return new ResponseEntity<>("There is no player with ID: " + id, HttpStatus.NOT_FOUND);

        else {
            Player player = playerRepository.findById(id).get();

            /* If player is connected to exercise.
            Deletes connected exercise.
            TODO: Fix with cascade.ALL in Exercise model. */
            if (exerciseRepository.existsByPlayer(player))
                exerciseRepository.delete(exerciseRepository.findByPlayer(player).get());

            playerRepository.delete(player);

            return new ResponseEntity<>("Player: " + player.getName() + " has been deleted", HttpStatus.OK);
        }
    }
}
