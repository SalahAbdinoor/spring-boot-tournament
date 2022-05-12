package com.paf.exercise.exercise.controller;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import com.paf.exercise.exercise.repository.ExerciseRepository;
import com.paf.exercise.exercise.repository.PlayerRepository;
import com.paf.exercise.exercise.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/exercise")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    PlayerRepository playerRepository;


    @GetMapping(path = "/test")
    public Iterable<Object> test() {

        var salah = new Player("Salah");
/*
        playerRepository.save(salah);

        playerRepository.save(new Player("Pedram"));
        playerRepository.save(new Player("Mooohammad"));

        var id = tournamentRepository.save(new Tournament(2500)).getId();

        tournamentRepository.save(new Tournament(5000));


 */
        return List.of(new String[]{"List of players: " + playerRepository.findAll(),"List of Tournaments: " + tournamentRepository.findAll(),"List of exercises: " + exerciseRepository.findAll()});

    }
}
