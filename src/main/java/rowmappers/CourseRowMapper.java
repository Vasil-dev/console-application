package rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
       int coureId = rs.getInt("course_id");
       String courseName = rs.getString("course_name");
       String courseDescription = rs.getString("course_description");
       return new Course(coureId,courseName, courseDescription);
    }
}
