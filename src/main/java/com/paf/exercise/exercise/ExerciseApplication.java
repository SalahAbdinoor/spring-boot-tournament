package com.paf.exercise.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ExerciseApplication {

    @Autowired
    InterviewScenarios scenario;

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

    /**
     * Setting up scenario:
     * <p>
     * There are 4 players partaking in 2 tournaments (high reward & mid reward)
     * <p>
     * --> the player 1 & 3 change tournament swap tournament
     * --> changing price name/reward on low reward -> ultra reward / 10000
     * --> delete player 1 and place new player in the tournament against player 4
     * --> change name of tournament
     *
     * @return - prints scenario in console
     */
    @Bean
    public CommandLineRunner initialSetup() {

        return args -> {

            System.out.println("\n---------------------------------------------------------------------");
            System.out.println("----------------------- Interview exercise --------------------------");
            System.out.println("---------------------------------------------------------------------");

            System.out.println("\n--> initializing players, tournaments & exercises \n");

            // init players, tournaments & exercises
            System.out.println(scenario.initPlayers());
            System.out.println(scenario.initTournaments());
            System.out.println(scenario.initExercises());

            System.out.println("\nCurrent state: \n" + scenario.currentState());

            System.out.println("\n--> Scenario 1: Change tournament spots of Ärtan and Piron \n");
            System.out.println(scenario.firstScenario());

            //System.out.println('\n' + scenario.currentState());

            System.out.println("\n--> Scenario 2: Change \n");
            System.out.println(scenario.secondScenario());

//            System.out.println('\n' + scenario.currentState());

            System.out.println("\n--> Scenario 3: Change \n");
            System.out.println(scenario.thirdScenario());



//            System.out.println('\n' + scenario.currentState());
/*

            System.out.println("\n--------------- TEST CASES ---------------------\n");

            Tournament midTournament = tournamentRepository.findByName(midTournamentName).get();
            Tournament highTournament = tournamentRepository.findByName(highTournamentName).get();

            List<Exercise> midTournamentExcercise = exerciseRepository.findByTournament(midTournament);
            List<Exercise> highTournamentExcercise = exerciseRepository.findByTournament(highTournament);

            System.out.println("--------------- Mid-reward players ---------------------");
            midTournamentExcercise.forEach((exercise) -> {
                System.out.println("Player in mid-reward tournament: " + exercise.getAttendingPlayers().getName());

            });

            System.out.println("\n--------------- High-reward players ---------------------");
            highTournamentExcercise.forEach((player) -> {
                System.out.println("Player in high-reward tournament: " + player.getAttendingPlayers().getName());
            });

 */
        };
    }
}
