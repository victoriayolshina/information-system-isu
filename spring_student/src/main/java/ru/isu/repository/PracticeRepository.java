package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isu.model.Faculty;
import ru.isu.model.Practice;
import ru.isu.model.Student;

import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByIdStudent(int student);

    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByStudent(Student student);

    @Query("SELECT p FROM Practice p WHERE p.id= ?1 ")
    Practice findPracticeById(int id);

    @Query("SELECT p FROM Practice p WHERE p.student= ?1 and p.id= ?2")
    Practice findPracticeByIdAndStudent(int studentId, int practiceId);

    @Query("SELECT p FROM Practice p WHERE p.student= ?1 and p.id= ?2")
    Practice findPracticeByIdAndStudent(Student studentId, int practiceId);
}
