package ru.isu.model.Custom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.*;

@Data
public class StudentCustomClass {
    private int faculty;

    private int id;

    private String name;

    private String surname;

    private String patronymic;

    private String username;

    private String password;
}
