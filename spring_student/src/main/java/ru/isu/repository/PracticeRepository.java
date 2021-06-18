package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ArrayUtils;
import ru.isu.model.Faculty;
import ru.isu.model.Practice;
import ru.isu.model.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByIdStudent(int student);

    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByStudent(Student student);

    @Query("SELECT p FROM Practice p WHERE p.id= ?1 ")
    Practice findPracticeById(int id);

    @Query("SELECT p.id FROM Practice p WHERE p.student= ?1")
    List<Integer> countPracticeByStudent(Student student);


}
