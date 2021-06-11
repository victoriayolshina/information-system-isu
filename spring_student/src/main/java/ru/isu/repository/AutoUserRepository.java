package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.model.AutoUser;

@Repository
//Запрос получения всей информации о пользователе
public interface AutoUserRepository extends JpaRepository<AutoUser, Long> {
    @Query("SELECT au FROM AutoUser au WHERE au.username = :user")
    AutoUser findAutoUserByUsername(@Param("user") String user);
}