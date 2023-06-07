package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.StudentRepository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.StudentService;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> get(int studentId) {
        logger.info("Getting student by Id: {}", studentId);
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Student> getAll() {
        logger.info("Getting all students: ");
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public void create(Student student) {
        logger.info("Creating student: {}",student);
        studentRepository.save(student);
    }

    @Override
    public void update(Student student, int studentId) {
        logger.info("Updating student by Id: {}: {}",studentId,student);
        student.setStudentId(studentId);
        studentRepository.save(student);
    }

    @Override
    public void delete(int studentId) {
        logger.info("Deleting student by Id: {}",studentId);
        studentRepository.deleteById(studentId);
    }
}
