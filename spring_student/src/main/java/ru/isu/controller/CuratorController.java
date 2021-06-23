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

    @Autowired
    AutoUserRepository autoUserRepository;

    @Autowired
    TypeOfPracticeRepository typeOfPracticeRepository;

    @Autowired
    PlaceOfPracticeRepositoty placeOfPracticeRepositoty;

    @Autowired
    SupervisorRepository supervisorRepository;


    //>>>
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCurator(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Curator curator = curatorRepository.findCuratorByUsername(token.getName());

        model.addAttribute("curator", curator);
        return "curatorhtml/curatorInfo";
    }


    //>>>
    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String allFaculty(Model model) {
        model.addAttribute("faculties", facultyRepository.findAll());
        return "curatorhtml/allfaculties";
    }


    //>>>
    @RequestMapping(value = "/faculty/new", method = RequestMethod.GET)
    public String addFaculty(Model model) {
        Faculty faculty = new Faculty();
        List<Direction> directions = directionRepository.findAll();
        model.addAttribute("faculty", faculty);
        model.addAttribute("directions", directions);
        return "curatorhtml/addfaculty";
    }


    //>>>
    @RequestMapping(value = "/faculty/new", method = RequestMethod.POST)
    @ResponseBody
    public String saveFaculty(
            @ModelAttribute("year") int year,
            @ModelAttribute("name") String name,
            @ModelAttribute("profile") String profile,
            @ModelAttribute("direction") int directionId,
            Authentication authentication) {
        System.out.println(year);

        Direction direction = directionRepository.findDirectionById(directionId);
        Faculty faculty = new Faculty(0, name, direction, year, profile);

        facultyRepository.save(faculty);
        return "/curator/faculty";
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.GET)
    public String getFaculty(@PathVariable("facultyId") int facultyId, Model model) {

        model.addAttribute("faculty", facultyRepository.findFacultyById(facultyId));
        model.addAttribute("directions", directionRepository.findAll());

        return "curatorhtml/faculty";
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.POST)
    @ResponseBody
    public String editFaculty(
            @ModelAttribute("year") int year,
            @ModelAttribute("name") String name,
            @ModelAttribute("profile") String profile,
            @ModelAttribute("direction") int directionId,
            @PathVariable("facultyId") int facultyId, Model model) {
        Direction direction = directionRepository.findDirectionById(directionId);
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        faculty.setDirection(direction);
        faculty.setId(facultyId);
        faculty.setName(name);
        faculty.setProfile(profile);
        faculty.setYear(year);
        facultyRepository.save(faculty);
        return "/curator/faculty";
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFaculty(@PathVariable("facultyId") int facultyId, Authentication authentication) {
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        List<Student> list =  studentRepository.findStudentsByFaculty(faculty);
        for (Student student : list) {
            studentRepository.delete(student);
        }
        facultyRepository.delete(faculty);
        return "";
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}/students", method = RequestMethod.GET)
    public String getAllStudent(@PathVariable("facultyId") int facultyId, Model model) {
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        model.addAttribute("students", studentRepository.findStudentsByFaculty(faculty));
        return "curatorhtml/students";
    }

    //>>>
    @RequestMapping(value = "/faculty/{facultyId}/students/new", method = RequestMethod.GET)
    public String setNewStudentByCurator(@PathVariable("facultyId") int facultyId, Model model) {
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        model.addAttribute("student", studentRepository.findStudentsByFaculty(faculty));
        model.addAttribute("faculty", faculty);
        return "curatorhtml/addStudent";
    }

    //>>>
    @RequestMapping(value = "/faculty/{facultyId}/students/new", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute StudentCustomClass studentCustomClassStudent, @PathVariable("facultyId") int facultyId) {

        AutoUser autoUser = new AutoUser();
        autoUser.setId(0);
        autoUser.setPassword(studentCustomClassStudent.getPassword());
        autoUser.setRole("ROLE_STUDENT");
        autoUser.setUsername(studentCustomClassStudent.getUsername());
        autoUserRepository.save(autoUser);

        Student student = new Student();
        student.setUsername(studentCustomClassStudent.getUsername());
        student.setPassword(studentCustomClassStudent.getPassword());
        student.setSurname(studentCustomClassStudent.getSurname());
        student.setPatronymic(studentCustomClassStudent.getPatronymic());
        student.setName(studentCustomClassStudent.getName());
        student.setFaculty(facultyRepository.findFacultyById(facultyId));
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/%s/students", facultyId);
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("facultyId") int facultyId, @PathVariable int studentId, Model model) {

        model.addAttribute("student", studentRepository.findStudentById(studentId));
        model.addAttribute("faculties", facultyRepository.findAll());
        return "curatorhtml/studentInfo";
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.POST)
    public String editStudent(@ModelAttribute Student student, @PathVariable int facultyId, @PathVariable int studentId) {
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        student.setId(studentId);
        student.setFaculty(faculty);
        studentRepository.save(student);
        return String.format("redirect:/curator/faculty/%d/students", facultyId);
    }


    //>>>
    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteStudentById(@PathVariable("studentId") int studentId) {
        Student student = studentRepository.findStudentById(studentId);
        studentRepository.delete(student);
        return "";
    }


    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(Authentication authentication, Model model) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Curator curator = curatorRepository.findCuratorByUsername(token.getName());

        model.addAttribute("practices", practiceRepository.findPracticesByCurator(curator));
        return "curatorhtml/practices";
    }

    @RequestMapping(value = "/practice/new", method = RequestMethod.GET)
    public String addPracticeGet(Model model) {
        model.addAttribute("typePractice",typeOfPracticeRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("placeOfPractice", placeOfPracticeRepositoty.findAll());
        model.addAttribute("supervisor", supervisorRepository.findAll());
        return "curatorhtml/editpractice";
    }

    @RequestMapping(value = "/practice/new", method = RequestMethod.POST)
    @ResponseBody
    public String addPracticePost(
            @ModelAttribute("starttime") Date starttime,
            @ModelAttribute("endtime") Date endtime,
            @ModelAttribute("typeOfPractice") int typeOfPracticeId,
            @ModelAttribute("student") int studentId,
            @ModelAttribute("post") String post,
            @ModelAttribute("place") int placeId,
            @ModelAttribute("supervisor") int superId,
            Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Curator curator = curatorRepository.findCuratorByUsername(token.getName());
        PlaceOfPractice placeOfPractice = placeOfPracticeRepositoty.findPlaceOfPracticeById(placeId);
        Supervisor supervisor = supervisorRepository.findSupervisorById(superId);
        TypeOfPractice typeOfPractice = typeOfPracticeRepository.findTypeOfPracticeById(typeOfPracticeId);
        Student student = studentRepository.findStudentById(studentId);

        Practice practice = new Practice();
        practice.setStarttime(starttime);
        practice.setEndtime(endtime);
        practice.setPost(post);
        practice.setCurator(curator);
        practice.setTypeOfPractice(typeOfPractice);
        practice.setStudent(student);
        practice.setPlaceOfPractice(placeOfPractice);
        practice.setSupervisor(supervisor);
        practice.setId(0);

        practiceRepository.save(practice);
        return "curator/practice/";
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
    @ResponseBody
    public String deletePractice(@PathVariable("practiceId") int practiceId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        practiceRepository.delete(practice);
        return " ";
    }
}
