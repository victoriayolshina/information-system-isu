package ru.isu.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Converter {
    private final static String baseFile = "user.json";

    public static void toJSON(Practice practice) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), practice);
        System.out.println("json created!");
    }

    public static Practice toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), Practice.class);
    }

}
