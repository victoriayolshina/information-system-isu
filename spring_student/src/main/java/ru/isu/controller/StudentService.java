package ru.isu.controller;

import org.springframework.stereotype.Service;
import ru.isu.model.Faculty;
import ru.isu.model.Student;

import java.util.LinkedList;
import java.util.List;

@Service("studentService")
public class StudentService {

    private List<Student> students = new LinkedList();

    List<Student> findAllStudents() {
        return this.students;
    }

    public void save(Student student) {
        this.students.add(student);
    }

    private Student createStudent(Integer studentId, String surname, String name, String patronymic, Faculty faculty, String username) {
        Student student = new Student();
        student.setId(studentId);
        student.setSurname(surname);
        student.setName(name);
        student.setPatronymic(patronymic);
        student.setFaculty(faculty);
        student.setUsername(username);
        return student;
    }
}
