package ru.isu.model.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoDates {
    Date from;
    Date to;

    public int getFirstDate(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(from);
        return gc.get(Calendar.YEAR);
    }

    public int getSecondDate(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(to);
        return gc.get(Calendar.YEAR);
    }
}
