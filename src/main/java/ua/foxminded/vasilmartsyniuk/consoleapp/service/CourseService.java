package ua.foxminded.vasilmartsyniuk.consoleapp.service;

import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {


    Optional<Course> get(int courseId);

    List<Course> getAll();

    void create(Course course);

    void update(Course course,int courseId);

    void delete(int courseId);
}
