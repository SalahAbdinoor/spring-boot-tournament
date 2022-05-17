package com.paf.exercise.exercise.repository;

import com.paf.exercise.exercise.model.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    @Override
    Optional<Tournament> findById(Long aLong);

    Optional<Tournament> findByName(String name);

    boolean existsByName(String name);

}
