package com.paf.exercise.exercise.repository;

import com.paf.exercise.exercise.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Override
    Optional<Player> findById(Long aLong);

    Player findByName(String name);

    boolean existsByName(String name);


}
