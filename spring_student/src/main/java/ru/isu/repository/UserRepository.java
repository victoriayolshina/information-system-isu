package ru.isu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.isu.model.Student;

public interface UserRepository {
//    @Query("SELECT s FROM Student s WHERE s.surname= :surname AND s.name = :name AND s.patronymic = :patronymic")
//    Student findByFullName(
//            @Param("fullname") String surname, String name, String patronymic
//    );
}
