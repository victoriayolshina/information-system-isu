package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isu.model.TypeOfDirection;

public interface TypeOfDirectionRepository extends JpaRepository<TypeOfDirection, Long> {
}
