package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isu.model.PlaceOfPractice;
import ru.isu.model.Practice;

import java.util.List;

@Repository
public interface PlaceOfPracticeRepositoty extends JpaRepository<PlaceOfPractice, Long> {
    @Query("SELECT p FROM PlaceOfPractice p WHERE p.id= ?1")
    PlaceOfPractice findPlaceOfPracticeById(int id);
}
