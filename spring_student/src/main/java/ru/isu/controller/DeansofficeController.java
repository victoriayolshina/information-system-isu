package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.Custom.Categories;
import ru.isu.model.Custom.Statistics;
import ru.isu.model.Custom.StatisticsCategories;
import ru.isu.model.Custom.TwoDates;
import ru.isu.model.Practice;
import ru.isu.model.TypeOfDirection;
import ru.isu.repository.*;
import java.util.*;

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
    PlaceOfPracticeRepositoty placeOfPractice;

    @Autowired
    SupervisorRepository supervisorRepository;

    @Autowired
    TypeOfDirectionRepository typeOfDirectionRepository;

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDeansOffice(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        System.out.println(token.getName());
        model.addAttribute("deansoffice", deansOfficeRepository.findDeansEmployeeByUsername(token.getName()));
        System.out.println(deansOfficeRepository.findDeansEmployeeByUsername(token.getName()));
        return "deansofficehtml/deansofficeInfo";
    }

    @RequestMapping("/curators")
    public String allCurator(Model model) {
        model.addAttribute("curators", curatorRepository.findAll());
        return "deansofficehtml/allcurators";
    }

    @RequestMapping(value = "/curators/{curatorId}", method = RequestMethod.GET)
    public String getCurator(@PathVariable("curatorId") int curatorId, Model model) {
        model.addAttribute("deansofficecurator", curatorRepository.findCuratorById(curatorId));
        return "deansofficehtml/curatorInfo";
    }

    @RequestMapping(value = "/curators/new", method = RequestMethod.GET)
    public String addCurator(Model model) {
        return "deansofficehtml/addCurator";
    }

    @RequestMapping(value = "/curators/new", method = RequestMethod.POST)
    public String saveCurator(Model model) {
        return "redirect:/deansofficehtml/curators";
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String allFaculty(Model model) {
        model.addAttribute("deansofficefaculties", facultyRepository.findAll());
        return "deansofficehtml/allfaculties";
    }

    @RequestMapping(value = "/faculty/{facultyId}", method = RequestMethod.GET)
    public String getFaculty(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("deansofficefaculty", facultyRepository.findFacultyById(facultyId));
        return "deansofficehtml/faculty";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students", method = RequestMethod.GET)
    public String getAllStudent(@PathVariable("facultyId") int facultyId, Model model) {
        model.addAttribute("deansofficestudents", studentRepository.findStudentsByFacultyId(facultyId));
        return "deansofficehtml/students";
    }

    @RequestMapping(value = "/faculty/{facultyId}/students/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("facultyId") int facultyId, @PathVariable int studentId, Model model) {
        model.addAttribute("deansofficestudent", studentRepository.findStudentById(studentId));
        return "deansofficehtml/studentInfo";
    }

    @RequestMapping(value = "/placeofpractice", method = RequestMethod.GET)
    public String getAllPlaceOfPractice(Model model) {
        model.addAttribute("allplaceofpractice", placeOfPractice.findAll());
        return "deansofficehtml/allplaceofpractice";
    }

    @RequestMapping(value = "/placeofpractice/{placeofpracticeId}", method = RequestMethod.GET)
    public String getPlaceOfPractice(@PathVariable("placeofpracticeId") int placeofpracticeId, Model model) {
        model.addAttribute("placeofpractice", placeOfPractice.findPlaceOfPracticeById(placeofpracticeId));
        return "deansofficehtml/placeofpractice";
    }

    @RequestMapping(value = "/placeofpractice/{placeofpracticeId}/supervisor", method = RequestMethod.GET)
    public String getAllSupervisor(@PathVariable("supervisor") int placeofpracticeId, Model model) {
        model.addAttribute("allsupervisors", supervisorRepository.findSupervisorsByPlaceOfPracticeId(placeofpracticeId));
        return "deansofficehtml/allsupervisors";
    }

    @RequestMapping(value = "/placeofpractice/{placeofpracticeId}/supervisor/{supervisorId}", method = RequestMethod.GET)
    public String getSupervisor(@PathVariable("placeofpracticeId") int placeofpracticeId, Model model) {
        model.addAttribute("supervisor", supervisorRepository.findSupervisorById(placeofpracticeId));
        return "deansofficehtml/supervisorInfo";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatistics(Model model) {
        return "deansofficehtml/statistics";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    @ResponseBody
    public StatisticsCategories postStatistics(@ModelAttribute TwoDates twoDates) {
        GregorianCalendar gc = new GregorianCalendar();
        int from, to, count;

        from = twoDates.getFirstDate();
        to = twoDates.getSecondDate();

        count = to - from + 1;


        List<TypeOfDirection> typeOfDirectionsList = typeOfDirectionRepository.findAll();
        Statistics[] arrayStatistics = new Statistics[typeOfDirectionsList.size()];

        for (TypeOfDirection _typeOfDirection : typeOfDirectionsList) {
            Statistics statistics = new Statistics(_typeOfDirection.getName(), count);
            arrayStatistics[_typeOfDirection.getId() - 1] = statistics;
        }

        List<Practice> practicesList = practiceRepository.getAllBetweenFromAndToOrderByStarttime(twoDates.getFrom(), twoDates.getTo());

//        List<Practice> practicesListByTime = practiceRepository.getAllOrderByStarttime();
//        for (Practice practice : practicesListByTime) {
//            System.out.println(practice);
//        }

        for (int i = 0; i < practicesList.size(); i++) {
            Date date = new Date(practicesList.get(i).getStarttime().getTime());
            gc.setTime(date);

            int year = gc.get(Calendar.YEAR);
            int indexForStatistics = year - from;

            if (year >= from && year <= to) {
                TypeOfDirection _typeOfDirection = practicesList.get(i).getPlaceOfPractice()
                        .getTypeOfDirection();
                arrayStatistics[_typeOfDirection.getId() - 1].add(indexForStatistics);
            }
        }

        for (int i = 0; i < arrayStatistics.length; i++) {
            for (int j = 0; j < count; j++) {
                arrayStatistics[i].add(j, (int) (Math.random() * 30 + 10));
            }
        }

        Categories categories = new Categories(count);
        for (int j = 0; j < count; j++) {
            categories.add(j, String.format("%d", from + j));
        }

//        for (Statistics arrayStatistic : arrayStatistics) {
//            System.out.println(arrayStatistic.toString());
//        }

        StatisticsCategories statisticsCategories = new StatisticsCategories(arrayStatistics, categories);
        return statisticsCategories;
    }
}
