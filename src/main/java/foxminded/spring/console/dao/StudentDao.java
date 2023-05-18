package foxminded.spring.console.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.foxminded.vasilmartsyniuk.consoleapp.Student;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentDao implements Dao<Student> {

    private final JdbcTemplate jdbcTemplate;

    public StudentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    public void update(Student student, String[] params) {
        String sql = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";
        jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName(), student.getStudentId());
    }

    @Override
    public void delete(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(sql, studentId);
    }

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            int studentId = rs.getInt("student_id");
            int groupId = rs.getInt("group_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return new Student(studentId, groupId, firstName, lastName);
        }
    }
}
