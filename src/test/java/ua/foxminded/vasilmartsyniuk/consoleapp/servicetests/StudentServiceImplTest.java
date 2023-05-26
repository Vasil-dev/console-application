package ua.foxminded.vasilmartsyniuk.consoleapp.servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.StudentDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
public class StudentServiceImplTest {

    @Mock
    private StudentDao studentDao;
    private StudentServiceImpl studentService;

    private final int STUDENT_ID = 1;
    private final int GROUP_ID = 1;

    @BeforeEach
    void setUpp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentServiceImpl(studentDao);
    }

    @Test
    void testGetStudent() {
        Student student = new Student(STUDENT_ID, GROUP_ID,"First Name","Last Name");
        when(studentDao.get(STUDENT_ID)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.get(STUDENT_ID);

        assertEquals(Optional.of(student),result);
        verify(studentDao).get(STUDENT_ID);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,1,"First Name","Last Name"));
        students.add(new Student(2,2,"First Name","Last Name"));
        students.add(new Student(3,3,"First Name","Last Name"));
        when(studentDao.getAll()).thenReturn(students);

        List<Student> result = studentService.getAll();

        assertEquals(students,result);
        verify(studentDao).getAll();
    }

    @Test
    void testCreatStudent() {
        Student student = new Student(STUDENT_ID,GROUP_ID,"First Name", "Last Name");
        studentService.create(student);

        verify(studentDao).create(student);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student(STUDENT_ID, GROUP_ID,"First Name","Last Name");
        studentService.update(student, STUDENT_ID);
        verify(studentDao).update(student, STUDENT_ID);
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student(STUDENT_ID,GROUP_ID,"First Name","Last NAme");
        studentService.delete(STUDENT_ID);
        verify(studentDao).delete(STUDENT_ID);
    }
}
