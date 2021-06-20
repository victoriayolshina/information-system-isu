package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.isu.model.Direction;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

}
