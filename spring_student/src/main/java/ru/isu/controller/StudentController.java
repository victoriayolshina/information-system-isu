package ru.isu.controller;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.*;
import ru.isu.model.Custom.CuratorSupervisorCustom;
import ru.isu.model.Custom.GantCustomClass;
import ru.isu.model.Custom.Values;
import ru.isu.repository.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    CuratorRepository curatorRepository;

    @Autowired
    TypeOfDirectionRepository typeOfDirectionRepository;


    //>>>
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudent(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());
        model.addAttribute("student", student);
        return "studenthtml/studentInfo";
    }

    //>>>
    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public String getAllPractice(Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        model.addAttribute("practices", practiceRepository.findPracticeByStudent(student));
        return "curatorhtml/practices";
    }

    @RequestMapping(value = "/practice/{practiceId}", method = RequestMethod.GET)
    public String getPractice(@PathVariable int practiceId, Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }

        model.addAttribute("practice", practiceRepository.findPracticeByStudent(student));
        return "curatorhtml/practice";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks", method = RequestMethod.GET)
    public String getAllTask(@PathVariable("practiceId") int practiceId, Model model, Authentication authentication) throws IOException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        //Проверка на наличие практики у студента практика с таким id
        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }

        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("tasks", taskRepository.findTasksByPractice(practice));
        Faculty faculty = student.getFaculty();
        Practice practicetempl = practiceRepository.findPracticeById(practiceId);
        PlaceOfPractice placeOfPractice = practicetempl.getPlaceOfPractice();
        Supervisor supervisor = practicetempl.getSupervisor();

        //Изменения формата выводимой даты
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<Task> tasks = taskRepository.findTasksByPractice(practice);
        SimpleDateFormat sdfTable = new SimpleDateFormat("dd.MM");

        //Получение года поступления студента и вычисление его курса с помощью этой даты
        int localDate = faculty.getYear();
        int diff = getCourse(localDate);
        String date = Integer.toString(diff);
        LocalDate todaydate = LocalDate.now();
        int todaydateYear = todaydate.getYear();
        String year = Integer.toString(todaydateYear);

//        String directionWithSlash = String.format("%s \\ %s", faculty.getDirection());

        StringBuffer sb = new StringBuffer();

        StringBuffer _stringBuffer = new StringBuffer("");
        for (Task _task : tasks) {
            _stringBuffer.append(String.format("%s -- %s & %s \\\\ \\hline \n",
                    sdfTable.format(_task.getDatastart()),
                    sdfTable.format(_task.getDataend()),
                    _task.getTask()
            ));
        }

        StringBuffer _stringBufferDescription = new StringBuffer("");
        for (Task _taskDescription : tasks) {
            _stringBufferDescription.append(String.format("%s \\\\ \n \n",
                    _taskDescription.getDescription()
            ));
        }

        //Блоки в файле шаблона, которые необходимо заменить
        String templYear = "[!*&^Год^&*!]";
        String templSurname = "[!*&^ФамилияСтудента^&*!]";
        String templSurnameRP = "[!*&^ФамилияСтудентаРП^&*!]";
        String templName = "[!*&^ИмяСтудента^&*!]";
        String templNameRP = "[!*&^ИмяСтудентаРП^&*!]";
        String templPatronymic = "[!*&^ОтчествоСтудента^&*!]";
        String templPatronymicRP = "[!*&^ОтчествоСтудентаРП^&*!]";
        String templCode = "[!*&^Код_направления^&*!]";
        String templDirection = "[!*&^Название_направления^&*!]";
        String templCourse = "[!*&^Курс^&*!]";
        String templTypeOfStudy = "[!*&^Форма_Обучения^&*!]";
        String templProfile = "[!*&^Профиль^&*!]";
        String templPlaceOfPractice = "[!*&^Место_практики^&*!]";
        String templStartTime = "[!*&^Дата_начала^&*!]";
        String templEndTime = "[!*&^Дата_конца^&*!]";
        String templCurator = "[!*&^Руководитель_кафедры^&*!]";
        String templEmail = "[!*&^Почта_руководителя^&*!]";
        String templSupervisor = "[!*&^Руководитель_По_Месту_Практики^&*!]";
        String templSupervisorPost = "[!*&^Должность_Руководителя_По_Месту_Практики^&*!]";
        String templPost = "[!*&^Должность^&*!]";
        String templTasks = "[!*&^ДатаИЗадача^&*!]";
        String templTaskDescription = "[!*&^ОписаниеЗадачи^&*!]";

        //Чтение шаблона по строкам
        InputStream inputStream = getClass().getResourceAsStream("/overleaf.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append("\n")
                    .append(line);
        }

        String supervisorFullName = String.format("%s %s %s", supervisor.getSurname(),supervisor.getName(), supervisor.getPatronymic());

        //Подстановка данных на места блоков
        sb = putData(sb, year, templYear);
        sb = putData(sb, student.getSurname(), templSurname);
        sb = putData(sb, student.getSurnameCase(), templSurnameRP);
        sb = putData(sb, student.getName(), templName);
        sb = putData(sb, student.getNameCase(), templNameRP);
        sb = putData(sb, student.getPatronymic(), templPatronymic);
        sb = putData(sb, student.getPatronymicCase(), templPatronymicRP);
        sb = putData(sb, faculty.getDirection().getCode(), templCode);
        sb = putData(sb, faculty.getDirection().getName(), templDirection);
        sb = putData(sb, date, templCourse);
        sb = putData(sb, practice.getFormOfStudy(), templTypeOfStudy);
        sb = putData(sb, placeOfPractice.getName(), templPlaceOfPractice);
        sb = putData(sb, practice.getProfile(), templProfile);
        sb = putData(sb, sdf.format(practice.getStarttime()), templStartTime);
        sb = putData(sb, sdf.format(practice.getEndtime()), templEndTime);
        sb = putData(sb, practice.getCuratorByDepartment(), templCurator);
        sb = putData(sb, practice.getСuratorEmail(), templEmail);
        sb = putData(sb, supervisorFullName, templSupervisor);
        sb = putData(sb, supervisor.getPost(), templSupervisorPost);
        sb = putData(sb, practice.getPost(), templPost);
        sb = putData(sb, _stringBuffer.toString(), templTasks);
        sb = putData(sb, _stringBufferDescription.toString(), templTaskDescription);

        model.addAttribute("template", sb.toString());

        return "studenthtml/tasks";
    }


    public int getCourse(int start_date) {
        // Дата поступления
        LocalDate date = LocalDate.of(start_date, 9, 1);

        // Дата сегодняшнего дня
        LocalDate end_date = LocalDate.now();

        //Период между этими датами
        Period diff = Period.between(date, end_date);

        //Получение года и месяцев разницы между датами
        int year = diff.getYears();
        int months = diff.getMonths();

        //Инициализация переменной для записи курса
        int datebetween = 0;

        if (months > 0) {
            datebetween = year + 1;
        } else {
            datebetween = year;
        }
        return datebetween;
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/new", method = RequestMethod.GET)
    public String addTaskGet(@PathVariable("practiceId") int practiceId, Authentication authentication, Model model) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }

        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("practice", practice);
        System.out.println(practice);
        return "studenthtml/addTask";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/new", method = RequestMethod.POST)
    public String addTaskPOST(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId, Authentication authentication) {

        Practice practice = practiceRepository.findPracticeById(practiceId);
        task.setPractice(practice);
        taskRepository.save(task);
        return String.format("redirect:/student/practice/%d/tasks", practiceId);
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.GET)
    public String getTaskGET(@PathVariable("taskId") int taskId, @PathVariable("practiceId") int practiceId,
                             Model model, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }
        Practice practice = practiceRepository.findPracticeById(practiceId);

        Task task = taskRepository.findTaskById(taskId);

        model.addAttribute("task", task);
        return "studenthtml/editTask";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", produces = {"application/xml; charset=UTF-8"}, method = RequestMethod.POST)
    public String editTask(Model model, @ModelAttribute Task task, @PathVariable("practiceId") int practiceId, @PathVariable("taskId") long taskId, Authentication authentication) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        taskRepository.delete(taskId);
        task.setPractice(practice);
        task.setId(taskId);
        taskRepository.save(task);
        return String.format("redirect:/student/practice/%d/tasks", practiceId);
//        return "studenthtml/editTask";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@PathVariable("taskId") long taskId, @PathVariable("practiceId") int practiceId, Authentication authentication) {
        taskRepository.delete(taskId);
        return "ok";
    }

    @RequestMapping(value = "/practice/{practiceId}/tasks/gant", method = RequestMethod.GET)
    public String getGantHTML(@PathVariable String practiceId, Authentication authentication) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Student student = studentRepository.findStudentByUsername(token.getName());

        List<Integer> integerList = practiceRepository.countPracticeByStudent(student);
        if (integerList.isEmpty() || !integerList.contains(practiceId)) {
            return String.format("redirect:/student/practice");
        }
        return "studenthtml/gant";
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

    @RequestMapping(value = "/practice/information/new", method = RequestMethod.GET)
    public String addInformationForTemplate(@PathVariable("practiceId") int practiceId, Authentication authentication, Model model) {
        return "studenthtml/information";
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/information", method = RequestMethod.GET)
    public String getInformationCuratorAndPlaseOfPractice(@PathVariable("practiceId") int practiceId, Authentication authentication, Model model) {
        List<Curator> curatorList = curatorRepository.findAll();
        List<TypeOfDirection> typeOfDirectionList = typeOfDirectionRepository.findAll();
        model.addAttribute("curators", curatorList);
        model.addAttribute("directions", typeOfDirectionList);
        return "studenthtml/addCuratorForm";
    }


    @RequestMapping(value = "/practice/{practiceId}/tasks/information", method = RequestMethod.POST)
    public String addInformationCuratorAndPlaseOfPractice(
            @ModelAttribute CuratorSupervisorCustom curatorSupervisorCustom,
            @PathVariable("practiceId") int practiceId, Authentication authentication) {
        System.out.println(curatorSupervisorCustom);
        return "studenthtml/information";
    }

    private StringBuffer putData(StringBuffer stringBuffer, String data, String template) {
        int elem = stringBuffer.indexOf(template, 0);
        while (elem != -1) {
            stringBuffer.replace(elem, elem + template.length(), data);
            elem = stringBuffer.indexOf(template, 0);
        }
        return stringBuffer;
    }
    private String putDataInTemplate(String str) {
        return String.format("[!*&^%s^&*!]", str);
    }
}