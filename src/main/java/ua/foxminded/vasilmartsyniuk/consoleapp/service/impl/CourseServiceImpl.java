package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseRepository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.CourseService;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> get(int courseId) {
        logger.info("Getting course by Id: {}", courseId);
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Course> getAll() {
        logger.info("Getting all courses: ");
        return courseRepository.findAll();
    }

    @Override
    public void create(Course course) {
        logger.info("Creating course: {}",course);
        courseRepository.save(course);
    }

    @Override
    public void update(Course course, int courseId) {
        logger.info("Updating course by Id: {}: {}",courseId,course);
        course.setCourseId(courseId);
        courseRepository.save(course);
    }

    @Override
    public void delete(int courseId) {
        logger.info("Deleting course by Id: {}",courseId);
        courseRepository.deleteById(courseId);
    }
}
