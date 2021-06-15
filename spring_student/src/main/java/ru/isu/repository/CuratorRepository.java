package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.model.Curator;
import ru.isu.model.Student;

import java.util.List;

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Long> {
    @Query("SELECT c FROM Curator c")
    List<Curator> findAll();

    @Query("SELECT c FROM Curator c WHERE c.id= :id")
    Curator findCuratorById(
            @Param("id") int curatorId
    );

    @Query("SELECT c FROM Curator c WHERE c.username= ?1")
    Curator findCuratorByUsername(String username);

}
