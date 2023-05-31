package ua.foxminded.vasilmartsyniuk.consoleapp.servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.CourseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;

    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseServiceImpl(courseDao);
    }

    @Test
    void testGetCourse() {
        int courseId = 1;
        Course course = new Course(courseId, "Math", "Math course");
        when(courseDao.get(courseId)).thenReturn(Optional.of(course));

        Optional<Course> result = courseService.get(courseId);

        assertEquals(Optional.of(course), result);
        verify(courseDao).get(courseId);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Math", "Math course"));
        courses.add(new Course(2, "Programming", "Programming course"));
        when(courseDao.getAll()).thenReturn(courses);

        List<Course> result = courseService.getAll();

        assertEquals(courses, result);
        verify(courseDao).getAll();
    }

    @Test
    void testCreateCourse() {
        Course course = new Course(1, "Math", "Math course");
        courseService.create(course);
        verify(courseDao).create(course);
    }

    @Test
    void testUpdateCourse() {
        int courseId = 1;
        Course course = new Course(courseId, "Math", "Math course");
        courseService.update(course, courseId);
        verify(courseDao).update(course, courseId);
    }

    @Test
    void testDeleteCourse() {
        int courseId = 1;
        courseService.delete(courseId);
        verify(courseDao).delete(courseId);
    }
}

