package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isu.model.Faculty;
import ru.isu.model.Practice;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query("SELECT f FROM Faculty f WHERE f.name= ?1")
    Faculty findFacultyByName(String name);

    @Query("SELECT f FROM Faculty f")
    List<Faculty> findAll();

    @Query("SELECT f FROM Faculty f WHERE f.id= ?1")
    Faculty findFacultyById(int id);

    @Query("SELECT f.id FROM Faculty f WHERE f.id= ?1")
    List<Integer> existsFacultyById(int id);
}
