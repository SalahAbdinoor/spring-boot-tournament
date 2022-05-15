package com.paf.exercise.exercise.repository;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    @Override
    Iterable<Exercise> findAll();

    @Override
    Optional<Exercise> findById(Long aLong);

    Optional<Exercise> findByTournament(Tournament tournament);

    Optional<Exercise> findByPlayer(Player player);

    boolean existsByTournament(Tournament tournament);

    boolean existsByPlayer(Player player);

}
