package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.isu.model.Direction;
import ru.isu.model.Faculty;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Query("SELECT d FROM Direction d WHERE d.id= ?1")
    Direction findDirectionById(int id);
}

