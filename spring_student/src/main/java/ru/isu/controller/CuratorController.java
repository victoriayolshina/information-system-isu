package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.*;
import ru.isu.model.Custom.StudentCustomClass;
import ru.isu.repository.*;

import java.sql.Date;
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

    @Autowired
    DirectionRepository directionRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCurator(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Curator curator = curatorRepository.findCuratorByUsername(token.getName());

        model.addAttribute("curator", curator);
        return "curatorhtml/curatorInfo";
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String allFaculty(Model model) {
        model.addAttribute("faculties", facultyRepository.findAll());
        return "curatorhtml/allfaculties";
    }

    @RequestMapping(value = "/faculty/new", method = RequestMethod.GET)
    public String addFaculty(Model model) {
        Faculty faculty = new Faculty();
        List<Direction> directions = directionRepository.findAll();
        model.addAttribute("faculty",faculty);
        model.addAttribute("directions",directions);
        return "curatorhtml/addfaculty";
    }


    @RequestMapping(value = "/faculty/new", method = RequestMethod.POST)
    public String saveFaculty(@ModelAttribute Faculty faculty, Model model) {
        System.out.println(faculty.toString());
//        facultyRepository.save(faculty);
        return "redirect:/curator/faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.GET)
    public String getFaculty(@PathVariable("facultyId") int facultyId, Model model) {
        if(facultyRepository.existsFacultyById(facultyId).isEmpty()){
            return String.format("redirect:/curator/faculty");
        }
        model.addAttribute("faculty", facultyRepository.findFacultyById(facultyId));
        return "curatorhtml/faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.POST)
    public String editFaculty(@ModelAttribute Faculty faculty, @PathVariable("facultyId") long facultyId, Model model) {
        System.out.println(faculty);
//        Faculty newfaculty = facultyRepository.findFacultyById((int) facultyId);
//        facultyRepository.delete(facultyId);
//        newfaculty.setName(faculty.getName());
//        newfaculty.setDirection(faculty.getDirection());
//        newfaculty.setYear(faculty.getYear());
//        facultyRepository.save(newfaculty);
//        !!!!!!!!!!!!!!
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
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        model.addAttribute("students", studentRepository.findStudentsByFaculty(faculty));
        return "curatorhtml/students";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/new", method = RequestMethod.GET)
    public String setNewStudentByCurator(@PathVariable("facultyId") int facultyId, Model model) {
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        model.addAttribute("student", studentRepository.findStudentsByFaculty(faculty));
        return "curatorhtml/addStudent";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/new", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute StudentCustomClass studentCustomClassStudent, @PathVariable String facultyId) {
        Student student = new Student();
        student.setUsername(studentCustomClassStudent.getUsername());
        student.setPassword(studentCustomClassStudent.getPassword());
        student.setSurname(studentCustomClassStudent.getSurname());
        student.setPatronymic(studentCustomClassStudent.getPatronymic());
        student.setName(studentCustomClassStudent.getName());
        student.setFaculty(facultyRepository.findFacultyById(studentCustomClassStudent.getFaculty()));
        System.out.println(student);
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/{%s}/students/", facultyId);
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("facultyId") int facultyId, @PathVariable int studentId, Model model) {
        model.addAttribute("student", studentRepository.findStudentById(studentId));
        return "studenthtml/studentInfo";
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
    public String deleteStudentById(@ModelAttribute Student student, @PathVariable int facultyId, @PathVariable int studentId) {
        studentRepository.delete((long) studentId);
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        student.setFaculty(faculty);
        student.setId(studentId);
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/%d/students/%d", facultyId, studentId);
    }

    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(Model model) {
        model.addAttribute("practices", practiceRepository.findAll());
//        model.addAttribute("starttime", practiceRepository.findPracticeByStartTime(practiceId));
//        model.addAttribute("endtime", practiceRepository.findPracticeByEndTime(practiceId));
        return "curatorhtml/practices";
    }

    @RequestMapping(value = "/practice/new", method = RequestMethod.GET)
    public String addPracticeGet(@ModelAttribute Practice practice, Model model) {
        practiceRepository.save(practice);
        return "redirect:/curator/practice/new";
    }

    @RequestMapping(value = "/practice/new", method = RequestMethod.POST)
    public String addPracticePost(@ModelAttribute Practice practice, @PathVariable("practiceId") long practiceId, Model model) {
        practiceRepository.save(practice);
        return "redirect:/curator/practice/new";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable int practiceId, Model model) {
        model.addAttribute("thepractice", practiceRepository.findPracticeById(practiceId));
//        model.addAttribute("starttime", practiceRepository.findPracticeByStartTime(practiceId));
//        model.addAttribute("endtime", practiceRepository.findPracticeByEndTime(practiceId));
        return "curatorhtml/practice";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.POST)
    public String editPractice(@ModelAttribute Practice practice, @PathVariable long practiceId, Model model) {
        Practice newpractice = practiceRepository.findPracticeById((int) practiceId);
        practiceRepository.delete(practiceId);
        newpractice.setStarttime(practice.getStarttime());
        newpractice.setEndtime(practice.getEndtime());
        practiceRepository.save(newpractice);
        //        !!!!!!!!!!!!!!
        return "redirect:/curatorhtml/editpractice";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.DELETE)
    public String deletePractice(@PathVariable("practiceId") int studentId, @PathVariable long practiceId, Model model) {
        practiceRepository.delete(practiceId);
        return String.format("redirect:/curator/practice/%d", practiceId);
    }
}
