package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.model.Faculty;
import ru.isu.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.username= :username")
    Student findStudentByUsername(
            @Param("username") String username
    );

    @Query("SELECT s FROM Student s")
    List<Student> findAll();

    @Query("SELECT s FROM Student s WHERE s.id= :id")
    Student findStudentById(
            @Param("id") int studentId
    );

    @Query("SELECT s FROM Student s WHERE s.faculty= ?1")
    List<Student> findStudentsByFaculty(Faculty faculty);

    @Query("SELECT s FROM Student s WHERE s.id= ?1")
    List<Student> findStudentsByFacultyId(int facultyId);

    @Query("UPDATE Student s SET s.surnameCase = ?1, s.nameCase = ?2, s.patronymicCase = ?3, s.formOfStudy= ?4 WHERE s.id = ?5")
    void update(
            String surnameCase,
            String nameCase,
            String patronymicCase,
            String formOfStudy,
            int id);
}
