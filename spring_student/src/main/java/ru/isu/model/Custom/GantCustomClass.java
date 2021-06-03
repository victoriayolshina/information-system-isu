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

    private String desc;

    private Values[] values;

    public void setValues(Values _values) {
        this.values = new Values[1];
        this.values[0] = _values;
    }
}
