package ru.isu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import ru.isu.model.TypeOfPractice;

public interface TypeOfPracticeRepository extends JpaRepository<TypeOfPractice, Long> {
    @Query("SELECT ty FROM TypeOfPractice ty WHERE ty.id= ?1")
    TypeOfPractice findTypeOfPracticeById(int id);
}
