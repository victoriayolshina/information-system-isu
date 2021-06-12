package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.*;
import ru.isu.model.Custom.CustomClass;
import ru.isu.repository.FacultyRepository;
import ru.isu.repository.PracticeRepository;
import ru.isu.repository.StudentRepository;
import ru.isu.repository.TaskRepository;

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

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("studentId") long studentId, Model model) {
        model.addAttribute("student", studentRepository.findStudentById(studentId));
        return "studentInfo";
    }

    @RequestMapping(value = "/{studentId}/practice", method = RequestMethod.GET)
    public String allPractice(@PathVariable("studentId") int studentId, Model model) {
        Student student = studentRepository.findStudentById(studentId);
        model.addAttribute("practice", practiceRepository.findPracticeByIdStudent(student));
        return "practices";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable("studentId") int studentId, Model model, @PathVariable String practiceId) {
        Student student = studentRepository.findStudentById(studentId);
        List<Practice> practices = practiceRepository.findPracticeByIdStudent(student);
        model.addAttribute("practice", practiceRepository.findPracticeByIdStudent(student));
        return "practice";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks", method = RequestMethod.GET)
    public String getTask(@PathVariable("practiceId") int practiceId, Model model, @PathVariable String studentId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("practiceId", practiceId);
        model.addAttribute("task", taskRepository.findTasksByIdPractice(practice));
        return "tasks";
    }

    //Переход на добавление записи
    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/new", method = RequestMethod.GET)
    public String getTaskAdd(Model model, @PathVariable("practiceId") int practiceId, @PathVariable String studentId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("practice", practice);
        return "addTask";
    }

    //Переход на страницу после добавления записи
    @RequestMapping(value = "/{studentId}/practice/{practiceId}/tasks/addTask/save", method = RequestMethod.POST)
    public String addTask(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId) {
        System.out.println(String.format("\n\n%s\n\n", task));
        Practice practice = practiceRepository.findPracticeById(practiceId);
        task.setPractice(practice);
        taskRepository.save(task);
        System.out.println(task);
        return "redirect:/task/{practiceId}";
    }
    //Переход на добавление записи
    @RequestMapping(value = "/{studentId}/practice/{practiceId}/task/editTask/{taskId}", method = RequestMethod.GET)
    public String getTaskEdit(Model model, @PathVariable("taskId") long taskId, @PathVariable("practiceId") long practiceId) {
        model.addAttribute("practiceId", practiceId);
        model.addAttribute("task", taskRepository.findTaskById(taskId));
        return "editTask";
    }

    @RequestMapping(value = "/{studentId}/practice/{practiceId}/task/editTask/{taskId}/save", method = RequestMethod.POST)
    public String editTask(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId, @PathVariable("taskId") long taskId) {
        taskRepository.delete(taskId);
        //System.out.println(String.format("\n\n%s\n\n",task));
        Practice practice = practiceRepository.findPracticeById(practiceId);
        //taskRepository.updateTaskById(task.getData(), task.getTask(), task.getDescription(),task.getId());
        task.setPractice(practice);
        task.setId(taskId);
        taskRepository.save(task);
        return "redirect:/tasks/{practiceId}";
    }

    //Удаление записи из общего списка
    @RequestMapping(value = "/{page}/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@PathVariable("taskId") long taskId) {
        taskRepository.delete(taskId);
        return "redirect:/tasks/{practiceId}/task";
    }
//    @RequestMapping("/students")
//    public String all(Model model) {
//        model.addAttribute("students", studentRepository.findAll());
//        return "students";
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
//        student.setFaculty(facultyRepository.findGroupById(customClassStudent.getFaculty()));
//        System.out.println(student);
//        //System.out.println(group_students);
//        studentRepository.save(student);
//        return "redirect:/students/students";
//    }
}
