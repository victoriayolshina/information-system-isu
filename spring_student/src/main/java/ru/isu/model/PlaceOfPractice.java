package ru.isu.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "placeofpractice")
@Getter
@Setter
@ToString
@Data
public class PlaceOfPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Fill in the field.")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeofdirection")
    private TypeOfDirection typeOfDirection;

    private String location;
}
