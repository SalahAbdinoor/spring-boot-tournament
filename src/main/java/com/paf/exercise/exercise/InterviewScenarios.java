package com.paf.exercise.exercise;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DISCLAIMER:
 * This class only exists to serve a couple of scenarios of how this API would work relation-mapping-wise.
 * Please do not think that this class is how a regular Service class would work.
 *
 */
@Service
public class InterviewScenarios {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    /**
     * setting up the initial players
     *
     * @return - prints amount of players created
     */
    public String initPlayers() {

        int count = 0;
        // List of player
        String[] listOfPlayers = new String[]{"Ärtan", "Pärtan", "Piron", "Paron"};

        // create & save new players
        for (String playerName : listOfPlayers) {
            playerRepository.save(new Player(playerName));
            count++;
        }
        return "Amount of players added: " + count;
    }

    /**
     * setting up the initial tournaments
     *
     * @return - prints amount of tournaments created
     */
    public String initTournaments() {

        int count = 0;

        // List of tournaments
        List<String[]> listOfTournaments = new ArrayList<>();

        listOfTournaments.add(new String[]{"high reward", "5000", "EUR"});
        listOfTournaments.add(new String[]{"mid reward", "2500", "EUR"});
        listOfTournaments.add(new String[]{"low reward", "1000", "EUR"});

        // create & save new tournament
        for (String[] tournament : listOfTournaments) {
            String name = tournament[0];
            String rewardAmount = tournament[1];
            String currency = tournament[2];

            try {

            tournamentRepository.save(new Tournament(name, Double.parseDouble(rewardAmount), currency));
            count++;

            } catch (IllegalArgumentException e) {
                System.out.println(e);

            }
        }

        return "Amount of tournaments added: " + count;

    }


    /**
     * setting up the initial exercises
     * <p>
     * exercises:
     * player 1 & 2 -> tournament 1
     * player 3 & 4 -> tournament 2
     *
     * @return - prints amount of exercises created
     */
    public String initExercises() {

        Player player1 = playerRepository.findByName("Ärtan");
        Player player2 = playerRepository.findByName("Pärtan");

        Player player3 = playerRepository.findByName("Piron");
        Player player4 = playerRepository.findByName("Paron");

        Tournament tournament1 = tournamentRepository.findByName("high reward").get();
        Tournament tournament2 = tournamentRepository.findByName("mid reward").get();

        exerciseRepository.save(new Exercise(tournament1, player1));
        exerciseRepository.save(new Exercise(tournament1, player2));

        exerciseRepository.save(new Exercise(tournament2, player3));
        exerciseRepository.save(new Exercise(tournament2, player4));

        return "Amount of exercises added: " + exerciseRepository.count();
    }

    public String firstScenario() {

        exerciseRepository.findAll().forEach(exercise -> {


        });

        return "First scenario";
    }

    public String secondScenario() {


        return "Second scenario";
    }

    public String thirdScenario() {


        return "Third scenario";
    }

    /**
     * find tournaments with similar ID and print players for that tournament
     *
     * @return
     */
    public Iterable<Exercise> currentState() {

        Map<Player, Tournament> tournamentsPlayers = new HashMap<>();

        Iterable<Exercise> listOfExercises = exerciseRepository.findAll();

        //var duplicates = StreamSupport.stream(listOfExercises.spliterator(), false).GroupBy(a => a).SelectMany(ab => ab.Skip(1).Take(1)).ToList();

        /*
        var a = StreamSupport.stream(listOfExercises.spliterator(), false)
                .forEach((exercise) -> {
                    var temp = "";
                    exercise.getTournament().getId();
                } );
*/
        /*
        for (Exercise exercise : listOfExercises) {

            System.out.println("exercise = " + exercise);


        }

         */

        return null;
    }
}
