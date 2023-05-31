package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.StudentDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.StudentService;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Optional<Student> get(int studentId) {
        logger.info("Getting student by Id: {}", studentId);
        return studentDao.get(studentId);
    }

    @Override
    public List<Student> getAll() {
        logger.info("Getting all students: ");
        return studentDao.getAll();
    }

    @Override
    public void create(Student student) {
        logger.info("Creating student: {}",student);
        studentDao.create(student);
    }

    @Override
    public void update(Student student, int studentId) {
        logger.info("Updating student by Id: {}: {}",studentId,student);
        studentDao.update(student, studentId);
    }

    @Override
    public void delete(int studentId) {
        logger.info("Deleting student by Id: {}",studentId);
        studentDao.delete(studentId);
    }
}
