package ru.isu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.isu.model.Faculty;

@Repository
public interface PlaceOfPractice extends CrudRepository<PlaceOfPractice, Long> {
    @Query("SELECT p FROM PlaceOfPractice p WHERE p.id= ?1")
    PlaceOfPractice findPlaceOfPracticeById(int id);
}
