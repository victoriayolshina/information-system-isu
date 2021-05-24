package ru.isu.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "practice")
@Getter
@Setter
@ToString
@Data
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student")
    private Student student;

    /*@ManyToOne( fetch = FetchType.EAGER)
    private PlaceOfPractice placeOfPractice;*/

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date starttime;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endtime;

    private String post;
}
