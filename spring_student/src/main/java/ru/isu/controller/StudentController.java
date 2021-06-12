package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.*;
import ru.isu.model.Custom.CustomClass;
import ru.isu.model.Custom.GantCustomClass;
import ru.isu.model.Custom.Values;
import ru.isu.repository.FacultyRepository;
import ru.isu.repository.PracticeRepository;
import ru.isu.repository.StudentRepository;
import ru.isu.repository.TaskRepository;

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


    @RequestMapping("/students")
    public String all(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @RequestMapping(value = "/add")
    public String addStudent(Model model) {
        Student student = new Student();
        CustomClass customClass = new CustomClass();
        List<Faculty> faculties = facultyRepository.findAll();
        System.out.println(faculties);
        model.addAttribute("student", student);
        model.addAttribute("customsStudent", customClass);
        model.addAttribute("faculty", faculties);
        return "addStudent";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute CustomClass customClassStudent) {
        Student student = new Student();
        student.setUsername(customClassStudent.getUsername());
        student.setPassword(customClassStudent.getPassword());
        student.setSurname(customClassStudent.getSurname());
        student.setPatronymic(customClassStudent.getPatronymic());
        student.setName(customClassStudent.getName());
        student.setFaculty(facultyRepository.findGroupById(customClassStudent.getFaculty()));
        System.out.println(student);
        //System.out.println(group_students);
        studentRepository.save(student);
        return "redirect:/students/students";
    }

    //Удаление записи из общего списка
    @RequestMapping(value = "/{page}/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@PathVariable("taskId") long taskId) {
        taskRepository.delete(taskId);
        return "redirect:/tasks/{practiceId}/task";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudent(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        System.out.println(token.getName());
        model.addAttribute("student", studentRepository.findStudentById(1));
        return "studentInfo";
    }

    @RequestMapping(value = "/{studentId}/practice", method = RequestMethod.GET)
    public String getAllPractice(@PathVariable("studentId") int studentId, Model model) {
        Student student = studentRepository.findStudentById(studentId);
        model.addAttribute("practices", practiceRepository.findPracticeByIdStudent(student));
        return "practices";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable("studentId") int studentId, @PathVariable int practiceId, Model model) {
        Student student = studentRepository.findStudentById(studentId);
        model.addAttribute("practices", practiceRepository.findPracticeByIdStudent(student));
        return "practice";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks", method = RequestMethod.GET)
    public String getAllTask(@PathVariable("practiceId") int practiceId, Model model) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("tasks", taskRepository.findTasksByIdPractice(practice));
        return "tasks";
    }


    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/new", method = RequestMethod.POST)
    public String addTask(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId) {
        task.setPractice(practiceRepository.findPracticeById(practiceId));

        return "redirect:";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/gant", method = RequestMethod.GET)
    public String getGantHTML() {
        return "gant";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/gant", method = RequestMethod.POST)
    @ResponseBody
    public List<GantCustomClass> gantGetData(@PathVariable("practiceId") int practiceId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        List<Task> arrayList = taskRepository.findTasksByIdPractice(practice);
        ArrayList<GantCustomClass> gantCustomClassArrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            GantCustomClass gantCustomClass = new GantCustomClass();
            Values values = new Values();
            Task task = arrayList.get(i);

            values.setFrom(task.getDatastart());
            values.setTo(task.getDataend());
            values.setLabel(task.getTask());
            System.out.println(values);

            gantCustomClass.setDesc(task.getTask());
            gantCustomClass.setValues(values);

            gantCustomClassArrayList.add(gantCustomClass);
        }

        return gantCustomClassArrayList;
    }


    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.GET)
    public String getTask(@PathVariable long taskId, Model model) {
        model.addAttribute("task", taskRepository.findTaskById(taskId));
        return "task";
    }


    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.POST)
    public String editTask(@ModelAttribute Task task, @PathVariable int practiceId, @PathVariable long taskId) {
        taskRepository.delete(taskId);

        Practice practice = practiceRepository.findPracticeById(practiceId);
        task.setPractice(practice);
        task.setId(taskId);
        taskRepository.save(task);

        return "redirect:/task/{practiceId}";
    }


    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@ModelAttribute Task task, @PathVariable long taskId) {
        taskRepository.delete(taskId);
        return "ok";
    }

}
