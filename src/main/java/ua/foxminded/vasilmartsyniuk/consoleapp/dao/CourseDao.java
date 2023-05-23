package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.rowmappers.CourseRowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDao implements Dao<Course> {

    private final JdbcTemplate jdbcTemplate;

    public CourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Course> get(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CourseRowMapper(), courseId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, course.getCourseId(), course.getCourseName());
    }

    @Override
    public void update(Course course, int courseId) {
        String sql = "UPDATE courses SET course_id = ?, course_name = ?, course_description = ? WHERE course_id = ?";
        jdbcTemplate.update(sql,course.getCourseId(), course.getCourseName(), course.getCourseDescription(),courseId);
    }

    @Override
    public void delete(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ? ";
        jdbcTemplate.update(sql,courseId);
    }
}
