package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.isu.model.*;
import ru.isu.repository.FacultyRepository;
import ru.isu.repository.StudentRepository;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FacultyRepository facultyRepository;


//    @RequestMapping(value = "/add")
//    public String addStudent(Model model){
//        Student student = new Student();
//        CustomClass customClass = new CustomClass();
//        List<Faculty> faculties = facultyRepository.findAll();
//        System.out.println(faculties);
//        model.addAttribute("student", student);
//        model.addAttribute("customsStudent", customClass);
//        model.addAttribute("faculty", faculties);
//        return "addStudent";
//    }

//    //Переход на страницу после добавления записи
//    @RequestMapping(value = "/{practiceId}/addStudent/save", method = RequestMethod.POST)
//    public String addStudent(@ModelAttribute Student student, @PathVariable("facultyId") int facultyId){
//        //AutoUser autoUser = autoUserRepository.findAutoUserByUsername(authentication.getName());
//        //fuel.setUsername(autoUser);
//        System.out.println(String.format("\n\n%s\n\n",student));
//        Faculty faculty =  facultyRepository.findGroupById(facultyId);
//        student.setFaculty(faculty);
//        StudentRepository.save(student);
//        System.out.println(student;
//        return "redirect:/tasks/{practiceId}";
//    }

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
        student.setSurname(customClassStudent.getSurname());
        student.setPatronymic(customClassStudent.getPatronymic());
        student.setName(customClassStudent.getName());
        student.setFaculty(facultyRepository.findGroupById(customClassStudent.getFaculty()));
        System.out.println(student);
        //System.out.println(group_students);
        studentRepository.save(student);
        return "redirect:/students/students";
    }
}
