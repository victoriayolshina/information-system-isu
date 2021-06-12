package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isu.model.Student;
import ru.isu.repository.FacultyRepository;
import ru.isu.repository.PracticeRepository;
import ru.isu.repository.StudentRepository;
import ru.isu.repository.TaskRepository;

@Controller
@RequestMapping("/practice")
public class PracticeController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PracticeRepository practiceRepository;

    @Autowired
    TaskRepository taskRepository;

//    @RequestMapping("/{studentId}")
//    public String all(Model model, @PathVariable("studentId") int studentId) {
//        Student student = studentRepository.findStudentById(studentId);
//        model.addAttribute("practice", practiceRepository.findPracticeByIdStudent(student));
//        return "practice";
//    }

    @RequestMapping("/gant")
    public String gant(){
        return "gant";
    }
}
