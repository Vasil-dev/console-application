package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.rowmappers.GroupRowMapper;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupDao implements Dao<Group> {

    private final JdbcTemplate jdbcTemplate;

    public GroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Group> get(int groupId) {
        String sql = "SELECT * FROM groups WHERE group_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new GroupRowMapper(), groupId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Group> getAll() {
        String sql = "SELECT * FROM groups";
        return jdbcTemplate.query(sql, new GroupRowMapper());
    }


    @Override
    public void create(Group group) {
        String sql = "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, group.getGroupId(), group.getGroupName());
    }

    @Override
    public void update(Group group, int groupId) {
        String sql = "UPDATE groups SET group_id = ?, group_name = ? WHERE group_id = ?";
        jdbcTemplate.update(sql, group.getGroupId(), group.getGroupName(), groupId);
    }

    @Override
    public void delete(int groupId) {
        String sql = "DELETE FROM groups WHERE group_id = ?";
        jdbcTemplate.update(sql, groupId);
    }
}
