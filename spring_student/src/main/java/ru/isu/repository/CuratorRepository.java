package ru.isu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.model.Curator;
import ru.isu.model.Student;

import java.util.List;

@Repository
public interface CuratorRepository extends CrudRepository<Curator, Long> {
    @Query("SELECT c FROM Curator c")
    List<Curator> findAll();

    @Query("SELECT c FROM Curator c WHERE c.id= :id")
    Curator findCuratorById(
            @Param("id") int curatorId
    );
}
