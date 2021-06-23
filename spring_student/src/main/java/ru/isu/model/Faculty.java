package ru.isu.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "direction")
    private Direction direction;

    private int year;

    private String profile;
}

