package com.paf.exercise.exercise;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExerciseApplication {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    TournamentRepository tournamentRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

    /**
     * Setting up scenarios:
     * -- 4 Players
     * -- 3 Tournaments
     * -- 4 Exercises
     */
    @Bean
    public CommandLineRunner initialSetup() {

        return args -> {

            System.out.println("\n---------------------------------------------------------------------"
                    + "\n----------------------- Interview exercise --------------------------"
                    + "\n---------------------------------------------------------------------");

            System.out.println("\n--> initializing players, tournaments & exercises \n");

            // init players, tournaments & exercises
            initPlayers();
            initTournaments();
            initExercises();

            System.out.println("\n--> current match-ups\n");

            printCurrentExercises();

            System.err.println("\nRead: SOLUTION.MD for instructions");
        };
    }

    /**
     * Setting up the initial players.
     *
     * @return amount of players created
     */
    private void initPlayers() {

        int count = 0;
        // List of player
        String[] listOfPlayers = new String[]{"Ärtan", "Pärtan", "Piron", "Paron"};

        // create & save new players
        for (String playerName : listOfPlayers) {
            playerRepository.save(new Player(playerName));
            count++;
        }
        System.out.println("Amount of players added: " + count);
    }

    /**
     * Setting up the initial tournaments.
     *
     * @return amount of tournaments created
     */
    private void initTournaments() {

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
                e.printStackTrace();
            }
        }
        System.out.println("Amount of tournaments added: " + count);
    }


    /**
     * Setting up the initial exercises.
     * <p>
     * exercises:
     * players: Ärtan & Pärtan -> tournament "high reward"
     * players: Piron & Paron -> tournament "mid reward"
     * <p>
     * prints: amount of exercises created
     */
    private void initExercises() {

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

        System.out.println("Amount of exercises added: " + exerciseRepository.count());
    }

    /**
     * prints current exercise
     */
    private void printCurrentExercises() {

        // Print each exercise
        for (Exercise exercise : exerciseRepository.findAll()) {

            System.out.println(exercise);
        }

    }
}
