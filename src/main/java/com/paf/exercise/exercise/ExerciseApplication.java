package com.paf.exercise.exercise;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialSetup(ExerciseRepository exerciseRepository, PlayerRepository playerRepository, TournamentRepository tournamentRepository) {

        return args -> {

            String highTournamentName = "high reward";
            String midTournamentName = "mid reward";
            String lowTournamentName = "low reward";

            /* Setting up players */
            Player player1 = playerRepository.save(new Player("Ärtan"));
            Player player2 = playerRepository.save(new Player("Pärtan"));

            Player player3 = playerRepository.save(new Player("Piron"));
            Player player4 = playerRepository.save(new Player("Paron"));

            /* Setting up tournaments*/
            Tournament highReward = tournamentRepository.save(new Tournament(highTournamentName,5000));
            Tournament midReward = tournamentRepository.save(new Tournament(midTournamentName, 2500));
            Tournament lowReward = tournamentRepository.save(new Tournament(lowTournamentName, 1000));

            /* Setting up exercises*/
            Exercise player1Exercise = exerciseRepository.save(new Exercise(highReward, player1));
            Exercise player2Exercise = exerciseRepository.save(new Exercise(highReward, player2));

            Exercise player3Exercise = exerciseRepository.save(new Exercise(midReward, player3));
            Exercise player4Exercise = exerciseRepository.save(new Exercise(midReward, player4));


            System.out.println("\n------------------- Players -----------------");
            System.out.println("Player 1: " + player1);
            System.out.println("Player 2: " + player2);
            System.out.println("PLayer 3: " + player3);
            System.out.println("PLayer 4: " + player4 + "\n");

            System.out.println("----------------- Tournaments -------------------");
            System.out.println("High reward: " + highReward);
            System.out.println("Mid reward: " + midReward);
            System.out.println("Low reward: " + lowReward + "\n");

            System.out.println("----------------- Exercises -------------------");
            System.out.println("Player 1 exercise: " + player1Exercise);
            System.out.println("Player 2 exercise: " + player2Exercise);
            System.out.println("Player 3 exercise: " + player3Exercise);
            System.out.println("Player 4 exercise: " + player4Exercise);

            /* ------------------------------------------------------------------------------- */
            System.out.println("\n--------------- TEST CASES ---------------------\n");

            Tournament midTournament = tournamentRepository.findByName(midTournamentName).get();
            Tournament highTournament = tournamentRepository.findByName(highTournamentName).get();

            List<Exercise> playersInMidReward = exerciseRepository.findByTournament(midTournament);
            List<Exercise> playersInHighReward = exerciseRepository.findByTournament(highTournament);

            System.out.println("--------------- Mid-reward players ---------------------");
            playersInMidReward.forEach((exercise) -> {
                System.out.println("Player in mid-reward tournament: " + exercise.getAttendingPlayers().getName());

            });

            System.out.println("\n--------------- High-reward players ---------------------");
            playersInHighReward.forEach((player) -> {
                System.out.println("Player in high-reward tournament: " + player.getAttendingPlayers().getName());
            });

        };
    }
}
