//package ru.isu.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import ru.isu.model.Custom.GantCustomClass;
//import ru.isu.model.Custom.Values;
//import ru.isu.model.Practice;
//import ru.isu.model.Task;
//import ru.isu.repository.PracticeRepository;
//import ru.isu.repository.StudentRepository;
//import ru.isu.repository.TaskRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/tasks")
//public class TaskController {
//    @Autowired
//    StudentRepository studentRepository;
//
//    @Autowired
//    PracticeRepository practiceRepository;
//
//    @Autowired
//    TaskRepository taskRepository;
//
//
//    @RequestMapping(value = "/{practiceId}/gant", method = RequestMethod.GET)
//    public String gantDiagramm(){
//        return "students/gant";
//    }
//
//
//    @PostMapping("/{practiceId}/gant/{if}")
//    public @ResponseBody List<GantCustomClass> gantDiagrammGetData(@PathVariable("practiceId") int practiceId) {
//        Practice practice = practiceRepository.findPracticeById(practiceId);
//        List<Task> arrayList = taskRepository.findTasksByPractice(practice);
//        ArrayList<GantCustomClass> gantCustomClassArrayList = new ArrayList<>();
//        System.out.println(arrayList);
//
//        for (int i = 0; i < arrayList.size(); i++) {
//            GantCustomClass gantCustomClass = new GantCustomClass();
//            Values values = new Values();
//            Task task = arrayList.get(i);
//
//            values.setFrom(task.getDatastart());
//            values.setTo(task.getDataend());
//            values.setLabel(task.getTask());
//            System.out.println(values);
//
//            gantCustomClass.setDesc(task.getTask());
//            gantCustomClass.setValues(values);
//
//            gantCustomClassArrayList.add(gantCustomClass);
//        }
//
//       System.out.println(gantCustomClassArrayList.toString());
//
//        return gantCustomClassArrayList;
//    }
//}
