package  ru.isu.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.isu.model.Student;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String direction;

    private Integer year;

    private String code;
}
