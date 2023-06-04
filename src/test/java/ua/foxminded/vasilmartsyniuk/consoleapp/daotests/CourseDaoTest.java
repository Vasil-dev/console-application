package ua.foxminded.vasilmartsyniuk.consoleapp.daotests;

import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        CourseDao.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/INSERT_DATA_COURSE.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)

class CourseDaoTest {

    @Autowired
    private CourseDao courseDao;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        courseDao = new CourseDao(entityManager);
    }

    @Test
    void testGetNonExistingCourse() {
        Optional<Course> course = courseDao.get(999);
        assertFalse(course.isPresent());
    }

    @Test
    void testGetExistingCourse() {
        Optional<Course> course = courseDao.get(1);
        assertTrue(course.isPresent());
        assertEquals(1,course.get().getCourseId());
        assertEquals("name",course.get().getCourseName());
        assertEquals("description",course.get().getCourseDescription());
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = courseDao.getAll();
        assertEquals(1,courses.size());
    }

    @Test
    void testCreateCourse() {
        Course newCourse = new Course(2,"name","description");
        courseDao.create(newCourse);

        List<Course> courses = courseDao.getAll();
        assertEquals(2,courses.size());
    }

    @Test
    void testUpdateCourse() {
        Course course = courseDao.get(1).orElse(null);
        assertNotNull(course);

        course.setCourseName("UpdatedName");
        courseDao.update(course, course.getCourseId());

        Course updatedCourse = courseDao.get(1).orElse(null);
        assertNotNull(updatedCourse);
        assertEquals("UpdatedName", updatedCourse.getCourseName());
    }


@Test
    void testDeleteCourse() {
        courseDao.delete(1);

        List<Course> courses = courseDao.getAll();
        assertEquals(0,courses.size());
    }
}