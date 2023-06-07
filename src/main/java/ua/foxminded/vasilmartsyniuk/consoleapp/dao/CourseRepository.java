package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
