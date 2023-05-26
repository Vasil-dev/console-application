package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Optional<Course> get(int courseId) {
        return courseDao.get(courseId);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public void create(Course course) {
        courseDao.create(course);
    }

    @Override
    public void update(Course course, int courseId) {
        courseDao.update(course, courseId);
    }

    @Override
    public void delete(int courseId) {
        courseDao.delete(courseId);
    }
}
