package com.paf.exercise.exercise.repository;

import com.paf.exercise.exercise.model.Exercise;
import com.paf.exercise.exercise.model.Player;
import com.paf.exercise.exercise.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Override
    List<Exercise> findAll();

    @Override
    Optional<Exercise> findById(Long aLong);

    Optional<Exercise> findByTournament(Tournament tournament);

    Optional<Exercise> findByPlayer(Player player);

    boolean existsByTournament(Tournament tournament);

    boolean existsByPlayer(Player player);

}
