package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.isu.model.Faculty;
import ru.isu.model.Practice;
import ru.isu.model.Student;

import java.util.List;

public interface PracticeRepository extends JpaRepository<Practice, Long> {
    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByIdStudent(Student student);

    @Query("SELECT p FROM Practice p WHERE p.id= ?1 ")
    Practice findPracticeById(int id);

}
