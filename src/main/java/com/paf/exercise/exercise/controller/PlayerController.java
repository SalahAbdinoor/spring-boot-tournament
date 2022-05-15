package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v2/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * @return list of players
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Get player by ID.
     *
     * @param id ID of player
     * @return Success || Error
     */
    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    String getPlayer(@PathVariable Long id) {

        if (!playerRepository.existsById(id))
            return "There is no player with ID: " + id;
        else
            return playerRepository.findById(id).get().toString();
    }

    /**
     * Add new player.
     *
     * @param name Name of player
     * @return Success || Error
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    String addPlayer(@RequestParam String name) {

        // If player already exist
        if (playerRepository.existsByName(name))
            return "Player already exists";

            // If player name is fewer than 4 characters
        else if (name.length() < 4)
            return "name must longer than 4 characters";

            // Successful creation of new player
        else {
            Player player = new Player(name);
            playerRepository.save(player);

            return "New player added: " + player;
        }
    }

    /**
     * Change the name of existing player.
     *
     * @param id      ID of player getting updated
     * @param newName New name of player
     * @return Success || Error
     */
    @PutMapping(path = "/put/{id}")
    public @ResponseBody
    String putPlayer(@PathVariable Long id,
                     @RequestParam String newName) {

        // If player doesn't exist
        if (!playerRepository.existsById(id))
            return "There is no player with ID: " + id;

            // If player-name is taken
        else if (playerRepository.existsByName(newName))
            return "Name is already in use, please choose a unique name";

            // If name is fewer than 4 characters
        else if (newName.length() < 4)
            return "Name must longer than 4 characters";

            // Successful name-change
        else {

            // Get player
            Player player = playerRepository.findById(id).get();
            String oldName = player.getName();

            // Set new-name and save to repository
            player.setName(newName);
            playerRepository.save(player);

            return "Player name changed: " + oldName + " -> " + newName;
        }
    }

    /**
     * Deletes player based on ID.
     *
     * @param id ID of player that is getting deleted
     * @return Success || Error
     */
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deletePlayer(@PathVariable Long id) {

        // If player doesn't exist
        if (!playerRepository.existsById(id))
            return "There is no player with ID: " + id;

        else {
            Player player = playerRepository.findById(id).get();

            /* If player is connected to exercise.
            Deletes connected exercise.
            TODO: Fix with cascade.ALL in Exercise model. */
            if (exerciseRepository.existsByPlayer(player))
                exerciseRepository.delete(exerciseRepository.findByPlayer(player).get());

            playerRepository.delete(player);
            return "Player: " + player.getName() + " has been deleted";
        }
    }
}
