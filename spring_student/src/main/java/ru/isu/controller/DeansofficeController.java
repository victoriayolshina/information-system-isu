package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.isu.repository.*;

@Controller
@RequestMapping("/deansoffice")
public class DeansofficeController {

    @Autowired
    CuratorRepository curatorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    PracticeRepository practiceRepository;

    @Autowired
    DeansOfficeRepository deansOfficeRepository;

    @Autowired
    PlaceOfPractice placeOfPractice;

    @Autowired
    SupervisorRepository supervisorRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDeansOffice(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        System.out.println(token.getName());
        model.addAttribute("deansoffice", deansOfficeRepository.findDeansEmployeeById(1));
        return "deansofficeInfo";
    }

    @RequestMapping("/curators")
    public String allCurator(Model model) {
        model.addAttribute("curators", curatorRepository.findAll());
        return "deansoffice/allcurators";
    }

    @RequestMapping(value = "/curators/{curatorId}", method = RequestMethod.GET)
    public String getCurator(@PathVariable("curatorId") int curatorId, Model model) {
        model.addAttribute("curator", curatorRepository.findCuratorById(curatorId));
        return "curator/curatorInfo";
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String allFaculty(Model model) {
        model.addAttribute("faculties", facultyRepository.findAll());
        return "curator/allfaculties";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.GET)
    public String getFaculty(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("faculty", facultyRepository.findFacultyById(facultyId));
        return "curator/faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students", method = RequestMethod.GET)
    public String getAllStudent(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("students", studentRepository.findStudentsByFacultyId(facultyId));
        return "curator/students";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("facultyId") int facultyId, @PathVariable int studentId, Model model) {
        model.addAttribute("student", studentRepository.findStudentById(studentId));
        return "students/studentInfo";
    }

    @RequestMapping(value = "/placeofpractice", method = RequestMethod.GET)
    public String getAllPlaceOfPractice(@PathVariable("placesofpractice") int placeofpracticeId, Model model) {
        model.addAttribute("allplacesofpractice", practiceRepository.findAll());
        return "deansoffice/allplacesofpractice";
    }

    @RequestMapping(value = "/placeofpractice/{placeofpracticeId}", method = RequestMethod.GET)
    public String getPlaceOfPractice(@PathVariable("placesofpractice") int placeofpracticeId, Model model) {
        model.addAttribute("placesofpractice", placeOfPractice.findPlaceOfPracticeById(placeofpracticeId));
        return "deansoffice/placeofpracticeInfo";
    }

    @RequestMapping(value = "/placeofpractice/{placeofpracticeId}/supervisor", method = RequestMethod.GET)
    public String getAllSupervisor(@PathVariable("supervisor") int placeofpracticeId, Model model) {
        model.addAttribute("supervisor", supervisorRepository.findSupervisorsByPlaceOfPracticeId(placeofpracticeId));
        return "deansoffice/allsupervisors";
    }

    @RequestMapping(value = "/placeofpractice/{placeofpracticeId}/supervisor/{supervisorId}", method = RequestMethod.GET)
    public String getSupervisor(@PathVariable("supervisor") int placeofpracticeId, Model model) {
        model.addAttribute("supervisor", supervisorRepository.findSupervisorById(placeofpracticeId));
        return "deansoffice/supervisorInfo";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatistics(Model model) {

        return "";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public String postStatistics(Model model) {

        return "";
    }

}
