package ua.foxminded.vasilmartsyniuk.consoleapp.servicetests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.StudentRepository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

 class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;
    private final int STUDENT_ID = 1;
    private final int GROUP_ID = 1;

    @BeforeEach
    void setUpp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    void testGetStudent() {
        Student student = new Student(STUDENT_ID, GROUP_ID,"First Name","Last Name");
        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.get(STUDENT_ID);

        Assertions.assertEquals(Optional.of(student), result);
        verify(studentRepository).findById(STUDENT_ID);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,1,"First Name","Last Name"));
        students.add(new Student(2,2,"First Name","Last Name"));
        students.add(new Student(3,3,"First Name","Last Name"));
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAll();

        Assertions.assertEquals(students, result);
        verify(studentRepository).findAll();
    }

    @Test
    void testCreatStudent() {
        Student student = new Student(STUDENT_ID,GROUP_ID,"First Name", "Last Name");
        studentService.create(student);

        verify(studentRepository).save(student);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student(STUDENT_ID, GROUP_ID,"First Name","Last Name");
        studentService.update(student, STUDENT_ID);
        student.setStudentId(STUDENT_ID);
        verify(studentRepository).save(student);
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student(STUDENT_ID,GROUP_ID,"First Name","Last NAme");
        studentService.delete(STUDENT_ID);
        verify(studentRepository).deleteById(STUDENT_ID);
    }
}
