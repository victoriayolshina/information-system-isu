package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.username= :username")
    Student findStudentByUsername(
            @Param("username") Student student
    );

    @Query("SELECT s FROM Student s")
    List<Student> findAll();

    @Query("SELECT s FROM Student s WHERE s.id= :id")
    Student findStudentById(
            @Param("id") int studentId
    );
}
