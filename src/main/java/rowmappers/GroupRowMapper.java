package rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {

    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        int groupId = rs.getInt("group_id");
        String groupName = rs.getString("group_name");
        return new Group( groupId, groupName);
    }
}
