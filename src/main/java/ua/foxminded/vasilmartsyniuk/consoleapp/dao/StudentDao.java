package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.rowmappers.StudentRowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDao implements Dao<Student> {

    private final JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Student> get(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new StudentRowMapper(), studentId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    @Override
    public void create(Student student) {
        String sql = "INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getStudentId(), student.getGroupId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public void update(Student student, int studentID) {
        String sql = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";
        jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName(), studentID);
    }

    @Override
    public void delete(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(sql, studentId);
    }
}
