package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.CourseService;

import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Optional<Course> get(int courseId) {
        logger.info("Getting course by Id: {}", courseId);
        return courseDao.get(courseId);
    }

    @Override
    public List<Course> getAll() {
        logger.info("Getting all courses: ");
        return courseDao.getAll();
    }

    @Override
    public void create(Course course) {
        logger.info("Creating course: {}",course);
        courseDao.create(course);
    }

    @Override
    public void update(Course course, int courseId) {
        logger.info("Updating course by Id: {}: {}",courseId,course);
        courseDao.update(course, courseId);
    }

    @Override
    public void delete(int courseId) {
        logger.info("Deleting course by Id: {}",courseId);
        courseDao.delete(courseId);
    }
}
