package com.paf.exercise.exercise.repository;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    @Override
    Optional<Exercise> findById(Long aLong);

    List<Exercise> findByTournament(Tournament tournament);

    List<Exercise> findByAttendingPlayers(Player player);

}
