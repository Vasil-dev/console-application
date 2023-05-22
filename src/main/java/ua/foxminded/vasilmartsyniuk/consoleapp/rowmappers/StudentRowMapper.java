package ua.foxminded.vasilmartsyniuk.consoleapp.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        int studentId = rs.getInt("student_id");
        int groupId = rs.getInt("group_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        return new Student(studentId, groupId, firstName, lastName);
    }
}
