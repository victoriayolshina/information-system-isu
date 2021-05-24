package ru.isu.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Fill in the field.")
    private String name;

    @NotBlank(message = "Fill in the field.")
    private String surname;

    @NotBlank(message = "Fill in the field.")
    private String patronymic;

    private String username;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty")
    private Faculty faculty;

    @Override
    public String toString() {
        return "Student{" + "studentId=" + id
                + ", surname=" + surname
                + ", name=" + name
                + ", patronymic=" + patronymic
                + ", username=" + username
                + ", faculty=" + faculty + '}';
    }
}
