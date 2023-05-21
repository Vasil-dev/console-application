package foxminded.spring.console.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ua.foxminded.vasilmartsyniuk.consoleapp.Student;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyTest.class)
//@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class StudentDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private StudentDao studentDao;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("school")
            .withUsername("postgres")
            .withPassword("1234");


    @BeforeEach
    void setUp() {
        studentDao = new StudentDao(jdbcTemplate.getDataSource());
        postgreSQLContainer.start();
    }
    @AfterAll
    static void tearDown() {
        postgreSQLContainer.stop();
    }


    @Test
    public void testGetExistingStudent() {
        Optional<Student> student = studentDao.get(30);
        assertTrue(student.isPresent());
        assertEquals(1, student.get().getStudentId());
        assertEquals(1, student.get().getGroupId());
        assertEquals("name", student.get().getFirstName());
        assertEquals("lastname", student.get().getLastName());
    }

    @Test
    public void test(){

    }

    @Test
    public void testGetNonExistingStudent() {
        Optional<Student> student = studentDao.get(1);
        assertFalse(student.isPresent());
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = studentDao.getAll();
        assertEquals(3, students.size());
    }

    @Test
    public void testCreateStudent() {
        Student newStudent = new Student(301, 2, "Jane", "Smith");
        studentDao.create(newStudent);

        List<Student> students = studentDao.getAll();
        assertEquals(4, students.size());
    }

    @Test
    public void testUpdateStudent() {
        Student student = studentDao.get(1).orElse(null);
        assertNotNull(student);

        student.setFirstName("UpdatedName");
        studentDao.update(student, student.getStudentId());

        Student updatedStudent = studentDao.get(1).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals("UpdatedName", updatedStudent.getFirstName());
    }

    @Test
    public void testDeleteStudent() {
        studentDao.delete(300);

        List<Student> students = studentDao.getAll();
        assertEquals(2, students.size());
    }

}
