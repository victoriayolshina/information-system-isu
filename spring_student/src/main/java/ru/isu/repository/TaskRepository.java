package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.isu.model.Practice;
import ru.isu.model.Student;
import ru.isu.model.Task;

import java.sql.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.practice= ?1")
    List<Task> findTasksByIdPractice(Practice practice);

//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Task t SET t.data = ?1, t.task = ?2, t.description = ?3 WHERE t.id = ?4")
//    void updateTaskById(Date data, String task, String description, long id);

    @Query("SELECT t FROM Task t WHERE t.id= ?1")
    Task findTaskById(long id);
}
