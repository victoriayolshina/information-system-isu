package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.isu.model.Practice;
import ru.isu.model.Supervisor;

import java.util.List;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    @Query("SELECT s FROM Supervisor s WHERE s.placeOfPractice= ?1")
    List<Supervisor> findSupervisorsByPlaceOfPracticeId(int placeofpractice);

    @Query("SELECT s FROM Supervisor s WHERE s.id= ?1 ")
    Practice findSupervisorById(int id);
}
