package ru.isu.model.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GantCustomClass {

    //public String name;

    public String desg;

    public Date date;

    public Values[] values;
}
