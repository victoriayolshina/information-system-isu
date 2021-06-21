package ru.isu.model.Custom;



import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Statistics {
    String name;
    int [] data;

    public Statistics(String _name, int length) {
        this.name = _name;
        this.data =  new int[length];
        for (int i = 0; i < this.data.length; i++) {
            this.data[i]=0;
        }
    }

    public void add(int index){
        data[index]++;
    }

    public void add(int index, int count){
        data[index]+=count;
    }
}
               