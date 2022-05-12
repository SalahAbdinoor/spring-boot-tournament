package com.paf.exercise.exercise.repository;

import com.paf.exercise.exercise.model.Tournament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    @Override
    Optional<Tournament> findById(Long aLong);

    List<Tournament> findByRewardAmount(double rewardAmount);

    Optional<Tournament> findByName(String name);

    boolean existsByName(String name);

}
