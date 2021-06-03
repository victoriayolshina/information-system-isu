package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isu.model.Custom.GantCustomClass;
import ru.isu.model.Practice;
import ru.isu.model.Task;
import ru.isu.repository.PracticeRepository;
import ru.isu.repository.StudentRepository;
import ru.isu.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PracticeRepository practiceRepository;

    @Autowired
    TaskRepository taskRepository;

//    public class TaskMappinng {
//
//    }

    @RequestMapping("/{practiceId}")
    public String all(Model model, @PathVariable("practiceId") int practiceId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("practiceId", practiceId);
        model.addAttribute("task", taskRepository.findTasksByIdPractice(practice));
        return "task";
    }

    //Переход на добавление записи
    @RequestMapping(value = "/{practiceId}/addTask")
    public String getTaskAdd(Model model, @PathVariable("practiceId") int practiceId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        model.addAttribute("practice", practice);
        return "addTask";
    }

    //Переход на страницу после добавления записи
    @RequestMapping(value = "/{practiceId}/addTask/save", method = RequestMethod.POST)
    public String addTask(@ModelAttribute Task task, @PathVariable("practiceId") int practiceId) {
        System.out.println(String.format("\n\n%s\n\n", task));
        Practice practice = practiceRepository.findPracticeById(practiceId);
        task.setPractice(practice);
        taskRepository.save(task);
        System.out.println(task);
        return "redirect:/tasks/{practiceId}";
    }

    //Переход на добавление записи
    @RequestMapping(value = "/{practiceId}/editTask/{taskId}", method = RequestMethod.GET)
    public String getTaskEdit(Model model, @PathVariable("taskId") long taskId, @PathVariable("practiceId") long practiceId) {
        model.addAttribute("practiceId", practiceId);
        model.addAttribute("task", taskRepository.findTaskById(taskId));
        return "editTask";
    }

    @RequestMapping(value = "/{practiceId}/editTask/{taskId}/save", method = RequestMethod.POST)
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

    @RequestMapping(value = "/{practiceId}/tasks/gant", method = RequestMethod.POST)
    @ResponseBody
    public String gantDiagramm(@PathVariable("practiceId") int practiceId) {
        Practice practice = practiceRepository.findPracticeById(practiceId);
        List<Task> arrayList = taskRepository.findTasksByIdPractice(practice);
        GantCustomClass gantCustomClass[];
        for (int i = 0; i <= arrayList.size(); i++){
            gantCustomClass[i];
        }
        System.out.println(gantCustomClass);
            return;
    }
}
