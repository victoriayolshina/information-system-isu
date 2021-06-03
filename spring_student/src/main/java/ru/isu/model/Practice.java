package ru.isu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student")
    @JsonProperty("student")
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
}
