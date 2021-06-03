package ru.isu.model.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Values {

    public Date from;
    public Date to;
    public String label;
}
