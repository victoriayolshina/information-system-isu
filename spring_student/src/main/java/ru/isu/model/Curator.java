package ru.isu.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "curator")
@Getter
@Setter
@ToString
@Data
public class Curator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Fill in the field.")
    private String name;

    @NotBlank(message = "Fill in the field.")
    private String surname;

    @NotBlank(message = "Fill in the field.")
    private String patronymic;

    private String degree;
    private String email;
}
