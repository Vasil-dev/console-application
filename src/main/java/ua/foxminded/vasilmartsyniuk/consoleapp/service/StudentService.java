package ua.foxminded.vasilmartsyniuk.consoleapp.service;

import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> get(int studentId);

    List<Student> getAll();

    void create(Student student);

    void update(Student student,int studentId);

    void delete(int studentId);
}
