//package ru.isu.controller;
//
//
//import org.springframework.http.converter.json.GsonBuilderUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//@Controller
//@RequestMapping("/")
//public class OverleafController {
//
//
//    // в этом классе показаны разные способы скопировать содержимое файла в строку
//    public static class ReadFileToString {
//        // построчное считывание файла
//        public static void main(String[] args) {
//            try {
//                File file = new File("**/src/java/template.txt");
//                //создаем объект FileReader для объекта File
//                FileReader fr = new FileReader(file);
//                //создаем BufferedReader с существующего FileReader для построчного считывания
//                BufferedReader reader = new BufferedReader(fr);
//                // считаем сначала первую строку
//                String line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line);
//                    // считываем остальные строки в цикле
//                    line = reader.readLine();
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
