package ua.foxminded.vasilmartsyniuk.consoleapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.StudentDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        StudentDao.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/INSERT_DATA_STUDENT.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class StudentDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDao(jdbcTemplate);
    }

    @Test
    void testGetExistingStudent() {
        Optional<Student> student = studentDao.get(1);
        assertTrue(student.isPresent());
        assertEquals(1, student.get().getStudentId());
        assertEquals(1, student.get().getGroupId());
        assertEquals("name", student.get().getFirstName());
        assertEquals("lastname", student.get().getLastName());
    }

    @Test
    void testGetNonExistingStudent() {
        Optional<Student> student = studentDao.get(999);
        assertFalse(student.isPresent());
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = studentDao.getAll();
        assertEquals(1, students.size());
    }

    @Test
    void testCreateStudent() {
        Student newStudent = new Student(2, 2, "Jane", "Smith");
        studentDao.create(newStudent);

        List<Student> students = studentDao.getAll();
        assertEquals(2, students.size());
    }

    @Test
    void testUpdateStudent() {
        Student student = studentDao.get(1).orElse(null);
        assertNotNull(student);

        student.setFirstName("UpdatedName");
        studentDao.update(student, student.getStudentId());

        Student updatedStudent = studentDao.get(1).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals("UpdatedName", updatedStudent.getFirstName());
    }

    @Test
    void testDeleteStudent() {
        studentDao.delete(1);

        List<Student> students = studentDao.getAll();
        assertEquals(0, students.size());
    }

}
