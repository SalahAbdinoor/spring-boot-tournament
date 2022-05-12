//package com.paf.exercise.exercise.controller;
//
//import com.paf.exercise.exercise.model.Exercise;
//import com.paf.exercise.exercise.model.Tournament;
//import com.paf.exercise.exercise.repository.ExerciseRepository;
//import com.paf.exercise.exercise.repository.PlayerRepository;
//import com.paf.exercise.exercise.repository.TournamentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(path = "/api/v2/tournament")
//public class ExerciseController {
//
//    @Autowired
//    PlayerRepository playerRepository;
//    @Autowired
//    TournamentRepository tournamentRepository;
//    @Autowired
//    ExerciseRepository exerciseRepository;
//
//    // TODO: Only for development
//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<Exercise> getTournaments() {
//
//        return exerciseRepository.findAll();
//    }
////
//    /**
//     * Find any exercise based on ID
//     *
//     * @param id - id of tournament
//     * @return tournament - Returns tournament object
//     */
//    @GetMapping(path = "/get")
//    public @ResponseBody
//    Tournament getTournament(@RequestParam long id) {
//
//        return tournamentRepository.findById(id).get();
//    }
//
//    /**
//     * Add new tournament
//     *
//     * @param name         - Name of tournament
//     * @param rewardAmount - reward amount
//     * @return tournament - Returns tournament object
//     */
//    @PostMapping(path = "/add")
//    public @ResponseBody
//    String addTournament(@RequestParam String name, @RequestParam double rewardAmount, @RequestParam String currency) {
//
//        // If tournament already exist
//        if (tournamentRepository.existsByName(name)) {
//            return "tournament already exists";
//
//        } else {
//            Tournament tournament = new Tournament(name, rewardAmount, currency);
//            tournamentRepository.save(tournament);
//
//            return "New tournament added: " + tournament;
//        }
//    }
//
//    /**
//     * Update/edit the name & reward-amount of existing tournament
//     *
//     * @param newName         - New name of tournament
//     * @param newRewardAmount - New amount for reward
//     * @return player - Returns player object
//     */
//    @PutMapping(path = "/update")
//    public @ResponseBody
//    String updateTournament(@RequestParam Long id, @RequestParam String newName, @RequestParam double newRewardAmount) {
//
//        // If ID doesn't exist
//        if (!tournamentRepository.existsById(id)) {
//            return "There is no tournament with ID: " + id;
//
//            // If tournament-name is taken
//        } else if (tournamentRepository.existsByName(newName)) {
//            return "Tournament already exists";
//
//        } else {
//            // Get tournament
//            Tournament tournament = tournamentRepository.findById(id).get();
//            String oldName = tournament.getName();
//            double oldRewardAmount = tournament.getRewardAmount();
//
//            // set new name & reward-amount and save to repository
//            tournament.setName(newName);
//            tournament.setRewardAmount(newRewardAmount);
//            tournamentRepository.save(tournament);
//
//            // Successful change
//            return "Tournament name changed: \n" + oldName + " -> " + newName
//                    + "\n" + oldRewardAmount + " -> " + newRewardAmount;
//        }
//    }
//
//    /**
//     * Deletes tournament based on ID
//     *
//     * @param id - ID of tournament that is getting deleted
//     * @return - how operation went
//     */
//    @DeleteMapping(path = "/delete")
//    public @ResponseBody
//    String deleteTournament(@RequestParam Long id) {
//
//        if (!tournamentRepository.existsById(id)) {
//            return "There is no tournament with ID: " + id;
//
//        } else {
//            Tournament tournament = tournamentRepository.findById(id).get();
//
//            tournamentRepository.delete(tournament);
//
//            return "Tournament: " + tournament.getName() + " has been deleted";
//        }
//    }
//
//}
