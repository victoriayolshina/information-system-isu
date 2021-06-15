package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.Custom.CustomClass;
import ru.isu.model.Faculty;
import ru.isu.model.Practice;
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
        return "curator/curatorInfo";
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String allFaculty(Model model) {
        model.addAttribute("faculties", facultyRepository.findAll());
        return "curator/allfaculties";
    }

    @RequestMapping(value = "/faculty/new", method = RequestMethod.GET)
    public String addFaculty(@PathVariable("facultyId") long facultyId, Model model) {

        return "curator/addfaculty";
    }

    @RequestMapping(value = "/faculty/new", method = RequestMethod.POST)
    public String saveFaculty(@ModelAttribute Faculty faculty, @PathVariable("facultyId") long facultyId, Model model) {
        facultyRepository.save(faculty);
        return "redirect:/curator/faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.GET)
    public String getFaculty(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("faculty", facultyRepository.findFacultyById(facultyId));
        return "curator/faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.POST)
    public String editFaculty(@ModelAttribute Faculty faculty, @PathVariable("facultyId") long facultyId, Model model) {

        return String.format("redirect:/curator/faculty");
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFaculty(@ModelAttribute Faculty faculty, @PathVariable("facultyId") long facultyId, Model model) {
        facultyRepository.delete(facultyId);
        return String.format("redirect:/curator/faculty/%d", facultyId);
    }

    @RequestMapping(value = "/faculty/{facultyId}/students", method = RequestMethod.GET)
    public String getAllStudent(@PathVariable("facultyId") int facultyId, Model model) {
        //Faculty faculty = facultyRepository.findFacultyById(facultyId);
        model.addAttribute("students", studentRepository.findStudentsByFacultyId(facultyId));
        return "curator/students";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/new", method = RequestMethod.GET)
    public String setNewStudentByCurator(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("students", studentRepository.findStudentsByFacultyId(facultyId));
        return "curator/students";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/new", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute CustomClass customClassStudent, @PathVariable String facultyId) {
        Student student = new Student();
        student.setUsername(customClassStudent.getUsername());
        student.setPassword(customClassStudent.getPassword());
        student.setSurname(customClassStudent.getSurname());
        student.setPatronymic(customClassStudent.getPatronymic());
        student.setName(customClassStudent.getName());
        student.setFaculty(facultyRepository.findFacultyById(customClassStudent.getFaculty()));
        System.out.println(student);
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/{%s}/students/",facultyId);
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("facultyId") int facultyId, @PathVariable int studentId, Model model) {
        model.addAttribute("student", studentRepository.findStudentById(studentId));
        return "students/studentInfo";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.POST)
    public String editStudent(@ModelAttribute Student student, @PathVariable int facultyId, @PathVariable int studentId) {
        studentRepository.delete((long) studentId);
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        student.setFaculty(faculty);
        student.setId(studentId);
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/%d/students/%d", facultyId, studentId);
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.DELETE)
    public String deleteSrudentById(@ModelAttribute Student student, @PathVariable int facultyId, @PathVariable int studentId) {
        studentRepository.delete((long) studentId);
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        student.setFaculty(faculty);
        student.setId(studentId);
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/%d/students/%d", facultyId, studentId);
    }

    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(@PathVariable("studentId") int studentId, Model model) {
        model.addAttribute("practices", practiceRepository.findPracticeByIdStudent(studentId));
        return "curator/practices";
    }

    @RequestMapping(value = "/practice/new", method = RequestMethod.GET)
    public String addPracticeGet(@ModelAttribute Practice practice, @PathVariable("practiceId") long practiceId, Model model) {
        practiceRepository.save(practice);
        return "redirect:/curator/practice/new";
    }

    @RequestMapping(value = "/practice/new", method = RequestMethod.POST)
    public String addPracticePost(@ModelAttribute Practice practice, @PathVariable("practiceId") long practiceId, Model model) {
        practiceRepository.save(practice);
        return "redirect:/curator/practice/new";
    }


    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable("studentId") int studentId, @PathVariable int practiceId, Model model) {
        model.addAttribute("practice", practiceRepository.findPracticeById(studentId));
        return "curator/practice";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.POST)
    public String editPractice(@ModelAttribute Practice practice, @PathVariable("studentId") int studentId, @PathVariable int practiceId, Model model) {
        practiceRepository.delete((long) practiceId);
        return "curator/editpractice";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.DELETE)
    public String deletePractice(@PathVariable("studentId") int studentId, @PathVariable long practiceId, Model model) {
        practiceRepository.delete(practiceId);
        return String.format("redirect:/curator/practice/%d", practiceId);
    }







}
