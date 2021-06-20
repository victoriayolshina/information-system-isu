package ru.isu.model.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuratorSupervisorCustom {
    int curator;
    String location;
    String name;
    String surname;
    String patronimic;
    int direction;
}
