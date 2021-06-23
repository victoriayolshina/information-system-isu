package ru.isu.model.Custom;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categories {
    String[] categories;

    public Categories(int length) {
        categories = new String[length];
    }

    public void add(int index, String categ) {
        categories[index] = categ;
    }

}
