package ru.isu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JsonProperty("id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student")
    @JsonProperty("student")
    @NotNull
    private Student student;

    /*@ManyToOne( fetch = FetchType.EAGER)
    private PlaceOfPractice placeOfPractice;*/

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("start time")
    private Date starttime;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("end time")
    private Date endtime;

    @JsonIgnore
    private String post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "placeofpractice")
    private PlaceOfPractice placeOfPractice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeofpractice")
    private TypeOfPractice typeOfPractice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor")
    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curator")
    private Curator curator;

    private String mark;


}
