package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.*;
import ru.isu.model.Custom.GantCustomClass;
import ru.isu.model.Custom.Values;
import ru.isu.repository.FacultyRepository;
import ru.isu.repository.PracticeRepository;
import ru.isu.repository.StudentRepository;
import ru.isu.repository.TaskRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    PracticeRepository practiceRepository;

    @Autowired
    TaskRepository taskRepository;

    //>>>
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudent(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());
        model.addAttribute("student", student);
        return "studenthtml/studentInfo";
    }

    //>>>
    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        model.addAttribute("practices", practiceRepository.findPracticeByStudent(student));
        return "curatorhtml/practices";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable int practiceId, Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }

        model.addAttribute("practice", practiceRepository.findPracticeByStudent(student));
        return "curatorhtml/practice";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks", method = RequestMethod.GET)
    public String getAllTask(@PathVariable("practiceId") int practiceId, Model model, Authentication authentication) throws IOException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }

//        StringBuffer sb = new StringBuffer();
//        String templ = "[!*&^ ^&*!]";
//
//        int count = 0;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/ru.isu/text.txt")));
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            for (int i = 0; i < line.length(); i++) {
//                if (line.indexOf(templ, i) != -1) {
//                    count++;
//                    i += templ.length();
//                }
//            }
//            sb.append("\n")
//                    .append(line);
//        }
//        System.out.println(sb);
//
//
//        for (int i = 0; i < count; i++) {
//            int elem = sb.indexOf(templ, 0);
//            String textTempl = String.format("%d вхождение текста", i);
//            sb.replace(elem, elem + templ.length(), "");
//            sb.insert(elem, textTempl);
//        }
//        System.out.println(sb);

        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("tasks", taskRepository.findTasksByPractice(practice));
        return "studenthtml/tasks";
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/overleaf", method = RequestMethod.GET)
    public String overleafTemplate(@PathVariable int practiceId, Model model, Authentication authentication) throws IOException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Faculty faculty = student.getFaculty();
        Practice practice = practiceRepository.findPracticeById(practiceId);
        PlaceOfPractice placeOfPractice = practice.getPlaceOfPractice();

//        File file = new File;

        StringBuffer sb = new StringBuffer();

        String templ = "[!*&^ ^&*!]";
        String file = "template.txt";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append("\n")
                    .append(line);
        }

//        while (sb.indexOf(templ, 0) != -1) {
//            int elem = sb.indexOf(templ, 0);
//            String textTempl = String.format(" вхождение текста");
//            sb.replace(elem, elem + templ.length(), "");
//            sb.insert(elem, textTempl);
//        }

        String textTempl = String.format("%s", student.getSurname());

//        int elem = sb.indexOf(templ, 0);
//        sb.replace(elem, elem + templ.length(), "");
//        sb.insert(elem, textTempl);

        sb = putData(sb, student.getSurname(), templ);
        model.addAttribute("template", sb.toString());

        return "studenthtml/overleaf";
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/new", method = RequestMethod.GET)
    public String addTaskGet(@PathVariable("practiceId") int practiceId, Authentication authentication, Model model) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }

        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("practice", practice);
        return "studenthtml/addTask";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/new", method = RequestMethod.POST)
    public String addTaskPOST(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId, Authentication authentication) {

        Practice practice = practiceRepository.findPracticeById(practiceId);
        task.setPractice(practice);
        taskRepository.save(task);
        return String.format("redirect:/student/practice/%d/tasks", practiceId);
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.GET)
    public String getTaskGET(@PathVariable("taskId") int taskId, @PathVariable("practiceId") int practiceId,
                             Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }
        Practice practice = practiceRepository.findPracticeById(practiceId);
        integerList = taskRepository.countTaskByPractice(practice);

        if (integerList.isEmpty() || !integerList.contains(taskId)) {
            return String.format("redirect:/student/practice/%d/tasks", practiceId);
        }

        Task task = taskRepository.findTaskById(taskId);

        model.addAttribute("task", task);
        return "studenthtml/task";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.POST)
    public String editTask(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId, @PathVariable("taskId") long taskId, Authentication authentication) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        taskRepository.delete(taskId);
        task.setPractice(practice);
        task.setId(taskId);
        taskRepository.save(task);
        return String.format("redirect:/student/practice/%d/tasks", practiceId);
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@PathVariable("taskId") long taskId, @PathVariable("practiceId") int practiceId, Authentication authentication) {
        taskRepository.delete(taskId);
        return "ok";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/gant", method = RequestMethod.GET)
    public String getGantHTML(@PathVariable String practiceId, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }
        return "studenthtml/gant";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/gant", method = RequestMethod.POST)
    @ResponseBody
    public List<GantCustomClass> gantGetData(@PathVariable("practiceId") int practiceId, Authentication authentication) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        List<Task> arrayList = taskRepository.findTasksByPractice(practice);
        ArrayList<GantCustomClass> gantCustomClassArrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            GantCustomClass gantCustomClass = new GantCustomClass();
            Values values = new Values();
            Task task = arrayList.get(i);

            values.setFrom(task.getDatastart());
            values.setTo(task.getDataend());
            values.setLabel(task.getTask());

            gantCustomClass.setDesc(task.getTask());
            gantCustomClass.setValues(values);

            gantCustomClassArrayList.add(gantCustomClass);
        }
        return gantCustomClassArrayList;
    }

    @RequestMapping(value = "/practice/information/new", method = RequestMethod.GET)
    public String addInformationForTemplate(@PathVariable("practiceId") int practiceId, Authentication authentication, Model model) {
        return "studenthtml/information";
    }

    private StringBuffer putData(StringBuffer stringBuffer, String data, String template){
        int elem = stringBuffer.indexOf(template, 0);
        stringBuffer.replace(elem, elem + template.length(), "");
        stringBuffer.insert(elem, data);

        return stringBuffer;
    }
}