package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v2/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    // TODO: Only for development
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Player> getPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Find any player based on ID
     *
     * @param id - id of player
     * @return player - Returns player object
     */
    @GetMapping(path = "/get")
    public @ResponseBody
    Player getPlayer(@RequestParam long id) {

        return playerRepository.findById(id).get();
    }

    /**
     * Add new player if player-name isn't already taken.
     *
     * @param name - Name of player
     * @return player - Returns player object
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    String addPlayer(@RequestParam String name) {

        // If player already exist
        if (playerRepository.existsByName(name)) {
            return "Player already exists";

        } else {
            Player player = new Player(name);
            playerRepository.save(player);

            return "New player added: " + player;
        }
    }

    /**
     * Change the name of existing player
     *
     * @param newName - New name of player
     * @return player - Returns player object
     */
    @PutMapping(path = "/put")
    public @ResponseBody
    String putPlayer(@RequestParam Long id, @RequestParam String newName) {

        // If ID doesn't exist
        if (!playerRepository.existsById(id)) {
            return "There is no player with ID: " + id;

            // If player-name is taken
        } else if (playerRepository.existsByName(newName)) {
            return "Player already exists";

        } else {
            // Get player
            Player player = playerRepository.findById(id).get();
            var oldName = player.getName();

            // set new name and save to repository
            player.setName(newName);
            playerRepository.save(player);

            // Successful name-change
            return "Player name changed: " + oldName + " -> " + newName;
        }
    }



    /**
     * Deletes player based on ID
     *
     * @param id - ID of player that is getting deleted
     * @return - how operation went
     */
    @DeleteMapping(path = "/delete")
    public @ResponseBody
    String deletePlayer(@RequestParam Long id) {

        if (!playerRepository.existsById(id)) {
            return "There is no player with ID: " + id;

        } else {
            Player player = playerRepository.findById(id).get();

            playerRepository.delete(player);

            return "PLayer: " + player.getName() + " has been deleted";
        }
    }
}
