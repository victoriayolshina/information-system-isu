package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
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


//    @RequestMapping("/students")
//    public String all(Model model) {
//        model.addAttribute("students", studentRepository.findAll());
//        return "curator/students";
//    }

//    @RequestMapping(value = "/add")
//    public String addStudent(Model model) {
//        Student student = new Student();
//        CustomClass customClass = new CustomClass();
//        List<Faculty> faculties = facultyRepository.findAll();
//        System.out.println(faculties);
//        model.addAttribute("student", student);
//        model.addAttribute("customsStudent", customClass);
//        model.addAttribute("faculty", faculties);
//        return "addStudent";
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String saveStudent(@ModelAttribute CustomClass customClassStudent) {
//        Student student = new Student();
//        student.setUsername(customClassStudent.getUsername());
//        student.setPassword(customClassStudent.getPassword());
//        student.setSurname(customClassStudent.getSurname());
//        student.setPatronymic(customClassStudent.getPatronymic());
//        student.setName(customClassStudent.getName());
//        student.setFaculty(facultyRepository.findFacultyById(customClassStudent.getFaculty()));
//        System.out.println(student);
//        //System.out.println(group_students);
//        studentRepository.save(student);
//        return "redirect:/students/students";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudent(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());
        model.addAttribute("student", student);
        return "students/studentInfo";
    }

    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        model.addAttribute("practices", practiceRepository.findPracticeByStudent(student));
        return "curator/practices";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable int practiceId, Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        model.addAttribute("practice", practiceRepository.findPracticeByStudent(student));
        return "curator/practice";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks", method = RequestMethod.GET)
    public String getAllTask(@PathVariable("practiceId") int practiceId, Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Practice practice = practiceRepository.findPracticeByIdAndStudent(student.getId(), practiceId);
        if(practice==null){
            return String.format("redirect:/student/practice");
        }

        model.addAttribute("tasks", taskRepository.findTasksByPractice(practice));
        return "students/tasks";
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/new", method = RequestMethod.GET)
    public String addTaskGet(@PathVariable("practiceId") int practiceId, Authentication authentication, Model model) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Practice practice = practiceRepository.findPracticeByIdAndStudent(student.getId(), practiceId);
        if(practice==null){
            return String.format("redirect:/student/practice");
        }
        model.addAttribute("practice", practice);
        return "students/addTask";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/new", method = RequestMethod.POST)
    public String addTaskPOST(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Practice practice = practiceRepository.findPracticeByIdAndStudent(student.getId(), practiceId);
        if(practice==null){
            return String.format("redirect:/student/practice");
        }

        task.setPractice(practice);
        taskRepository.save(task);
        return String.format("redirect:/student/practice/%d/tasks",practiceId);
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.GET)
    public String getTaskGET(@PathVariable("taskId") int taskId, @PathVariable("practiceId") int practiceId,
                          Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Practice practice = practiceRepository.findPracticeByIdAndStudent(student, practiceId);
        if(practice==null){
            return String.format("redirect:/student/practice");
        }
        Task task = taskRepository.findTaskByIdAndStudent(practice, taskId);
        if(task==null){
            return String.format("redirect:/student/practice/%d",practiceId);
        }

        model.addAttribute("task", taskRepository.findTaskById(taskId));
        return "students/task";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.POST)
    public String editTask(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId, @PathVariable("taskId") long taskId, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Practice practice = practiceRepository.findPracticeByIdAndStudent(student, practiceId);
        if(practice==null){
            return String.format("redirect:/student/practice");
        }

        taskRepository.delete(taskId);
        task.setPractice(practice);
        task.setId(taskId);
        taskRepository.save(task);
        return String.format("redirect:/student/practice/%d/tasks", practiceId);
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@PathVariable("taskId") long taskId, @PathVariable("practiceId") int practiceId, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        Practice practice = practiceRepository.findPracticeByIdAndStudent(student, practiceId);
        if(practice==null){
            return String.format("redirect:/student/practice");
        }

        taskRepository.delete(taskId);
        return "ok";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/gant", method = RequestMethod.GET)
    public String getGantHTML() {
        return "students/gant";
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
}
