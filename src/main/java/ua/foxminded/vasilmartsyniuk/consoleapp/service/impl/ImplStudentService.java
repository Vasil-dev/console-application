package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import ua.foxminded.vasilmartsyniuk.consoleapp.dao.StudentDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.StudentService;

import java.util.List;
import java.util.Optional;

public class ImplStudentService implements StudentService {

    private final StudentDao studentDao;

    public ImplStudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Optional<Student> get(int studentId) {
        return studentDao.get(studentId);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(Student student, int studentId) {
        studentDao.update(student, studentId);
    }

    @Override
    public void delete(int studentId) {
        studentDao.delete(studentId);
    }
}
