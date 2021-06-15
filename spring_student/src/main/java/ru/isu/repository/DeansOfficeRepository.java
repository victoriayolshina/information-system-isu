package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.model.Curator;
import ru.isu.model.DeansEmployee;

@Repository
public interface DeansOfficeRepository extends JpaRepository<DeansEmployee, Long> {

    @Query("SELECT d FROM DeansEmployee d WHERE d.id= :id")
    DeansEmployee findDeansEmployeeById(
            @Param("id") int deansOfficeId
    );
}
