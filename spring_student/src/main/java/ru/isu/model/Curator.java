package ru.isu.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "curator")
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private String username;

    private String password;
}
