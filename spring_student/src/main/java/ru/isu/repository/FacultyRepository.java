package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isu.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query("SELECT f FROM Faculty f WHERE f.name= ?1")
    Faculty findGroupByName(String name);

    @Query("SELECT f FROM Faculty f")
    List<Faculty> findAll();

    @Query("SELECT f FROM Faculty f WHERE f.id= ?1")
    Faculty findGroupById(int id);
}
