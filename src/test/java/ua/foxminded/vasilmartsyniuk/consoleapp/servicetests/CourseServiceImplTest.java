package ua.foxminded.vasilmartsyniuk.consoleapp.servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseRepository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.CourseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseServiceImpl(courseRepository);
    }

    @Test
    void testGetCourse() {
        int courseId = 1;
        Course course = new Course(courseId, "Math", "Math course");
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        Optional<Course> result = courseService.get(courseId);

        assertEquals(Optional.of(course), result);
        verify(courseRepository).findById(courseId);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Math", "Math course"));
        courses.add(new Course(2, "Programming", "Programming course"));
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.getAll();

        assertEquals(courses, result);
        verify(courseRepository).findAll();
    }

    @Test
    void testCreateCourse() {
        Course course = new Course(1, "Math", "Math course");
        courseService.create(course);
        verify(courseRepository).save(course);
    }

    @Test
    void testUpdateCourse() {
        int courseId = 1;
        Course course = new Course(courseId, "Math", "Math course");
        courseService.update(course, courseId);
        course.setCourseId(courseId);
        verify(courseRepository).save(course);
    }

    @Test
    void testDeleteCourse() {
        int courseId = 1;
        courseService.delete(courseId);
        verify(courseRepository).deleteById(courseId);
    }
}

