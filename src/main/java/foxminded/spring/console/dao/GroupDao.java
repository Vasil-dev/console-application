package foxminded.spring.console.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.Group;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GroupDao implements Dao<Group> {

    private final JdbcTemplate jdbcTemplate;

    public GroupDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Group> get(int groupId) {
        String sql = "SELECT * FROM groups WHERE group_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new GroupDao.GroupRowMapper(), groupId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Group> getAll() {
        String sql = "SELECT * FROM groups";
        return jdbcTemplate.query(sql, new GroupDao.GroupRowMapper());
    }


    @Override
    public void create(Group group) {
        String sql = "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, group.getGroupId(), group.getGroupName());
    }

    @Override
    public void update(Group group, String[] params) {
        String sql = "UPDATE groups SET group_id = ?, group_name = ? WHERE group_id = ?";
        jdbcTemplate.update(sql, group.getGroupId(), group.getGroupName());
    }

    @Override
    public void delete(int groupId) {
        String sql = "DELETE FROM groups WHERE group_id = ?";
        jdbcTemplate.update(sql,groupId);
    }

    private static class GroupRowMapper implements RowMapper<Group> {
        @Override
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            int groupId = rs.getInt("group_id");
            String groupName = rs.getString("group_name");
            return new Group(groupId, groupName);
        }
    }

}
