package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isu.model.Practice;
import ru.isu.model.Student;

import java.sql.Date;
import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByIdStudent(int student);

    @Query("SELECT p FROM Practice p WHERE p.student= ?1")
    List<Practice> findPracticeByStudent(Student student);

    @Query("SELECT p FROM Practice p WHERE p.id= ?1 ")
    Practice findPracticeById(int id);

    @Query("SELECT p FROM Practice p WHERE p.starttime= ?1 ")
    Practice findPracticeByStartTime(int starttime);

    @Query("SELECT p FROM Practice p WHERE p.endtime= ?1 ")
    Practice findPracticeByEndTime(int endtime);

    @Query("SELECT p.id FROM Practice p WHERE p.student= ?1")
    List<Integer> countPracticeByStudent(Student student);

    @Query("SELECT p FROM Practice p ORDER BY p.starttime ASC")
    List<Practice> getAllOrderByStarttime();

    @Query("SELECT p FROM Practice p WHERE  p.starttime BETWEEN ?1 and ?2 ORDER BY p.starttime")
    List<Practice> getAllBetweenFromAndToOrderByStarttime(Date from, Date to);

}
