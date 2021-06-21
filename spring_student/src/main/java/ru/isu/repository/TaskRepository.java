package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.model.Practice;
import ru.isu.model.Student;
import ru.isu.model.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.practice= ?1 ORDER BY t.datastart")
    List<Task> findTasksByPractice(Practice practice);

    @Query("SELECT t FROM Task t WHERE t.practice= :practiceId")
    List<Task> findTasksByPracticeId(@Param("practiceId") int practiceId);

    @Query("SELECT t FROM Task t WHERE t.id= ?1")
    Task findTaskById(long id);

    @Query("SELECT t FROM Task t WHERE t.datastart BETWEEN ?1 and ?2")
    List<Task> findTasksBetweenTwoDates(Date from, Date to);

}
