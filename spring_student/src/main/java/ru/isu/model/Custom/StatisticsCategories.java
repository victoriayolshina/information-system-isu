package ru.isu.model.Custom;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsCategories {
    public Statistics[] statistics;
    public Categories categories;
}
