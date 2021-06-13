package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.Custom.CustomClass;
import ru.isu.model.Faculty;
import ru.isu.model.Student;
import ru.isu.repository.CuratorRepository;
import ru.isu.repository.FacultyRepository;
import ru.isu.repository.PracticeRepository;
import ru.isu.repository.StudentRepository;

import java.util.List;

@Controller
@RequestMapping("/curator")
public class CuratorController {

    @Autowired
    CuratorRepository curatorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    PracticeRepository practiceRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCurator(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        System.out.println(token.getName());
        model.addAttribute("curator", curatorRepository.findCuratorById(1));
        return "curatorInfo";
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String allFaculty(Model model) {
        model.addAttribute("faculties", facultyRepository.findAll());
        return "allfaculties";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.GET)
    public String getFaculty(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("faculty", facultyRepository.findFacultyById(facultyId));
        return "faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}/new", method = RequestMethod.POST)
    public String newFaculty(@ModelAttribute Faculty faculty, @PathVariable("facultyId") int facultyId, Model model) {
        facultyRepository.save(faculty);
        return String.format("redirect:/faculty/%d", facultyId);
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.POST)
    public String editFaculty(@ModelAttribute Faculty faculty, @PathVariable("facultyId") int facultyId, Model model) {
        facultyRepository.delete(faculty);

        return String.format("redirect:/faculty/%d", facultyId);
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@ModelAttribute Faculty faculty, @PathVariable("facultyId") long facultyId, Model model) {
        facultyRepository.delete(facultyId);
        return String.format("redirect:/faculty/%d", facultyId);
    }


    @RequestMapping(value = "/faculty/{facultyId}/students", method = RequestMethod.GET)
    public String getAllStudent(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("students", studentRepository.findStudentsByFacultyId(facultyId));
        return "allstudents";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("facultyId") int facultyId, @PathVariable int studentId, Model model) {
        model.addAttribute("student", studentRepository.findStudentById(studentId));
        return "student";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/new ")
    public String addStudent(Model model, @PathVariable String facultyId) {
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
        student.setFaculty(facultyRepository.findFacultyById(customClassStudent.getFaculty()));
        System.out.println(student);
        //System.out.println(group_students);
        studentRepository.save(student);
        return String.format("redirect:/students/students");
    }

    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("practices", practiceRepository.findPracticeByIdStudent(studentId));
        return "practices";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable("studentId") int studentId, @PathVariable int practiceId, Model model) {
        model.addAttribute("practice", practiceRepository.findPracticeById(studentId));
        return "practice";
    }

}
