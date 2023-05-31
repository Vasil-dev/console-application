package ua.foxminded.vasilmartsyniuk.consoleapp.daotests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.GroupDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes ={
        GroupDao.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/INSERT_DATA_GROUP.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)

class GroupDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private GroupDao groupDao;

    @BeforeEach
    void setUp() {
        groupDao = new GroupDao(jdbcTemplate);
    }

    @Test
    void testGetExistingGroup() {
        Optional<Group> group = groupDao.get(1);
        assertTrue(group.isPresent());
        assertEquals(1,group.get().getGroupId());
        assertEquals("name", group.get().getGroupName());
    }

    @Test
    void testGetNotExistingGroup() {
        Optional<Group> group = groupDao.get(999);
        assertFalse(group.isPresent());
    }

    @Test
    void testGetAllGroups() {
        List<Group> groups = groupDao.getAll();
        assertEquals(1,groups.size());
    }

    @Test
    void testCreateGroup() {
        Group newGroup = new Group(2,"name");
        groupDao.create(newGroup);

        List<Group> groups = groupDao.getAll();
        assertEquals(2,groups.size());
    }

    @Test
    void testUpdateGroup() {
        Group group = groupDao.get(1).orElse(null);
        assertNotNull(group);

        group.setGroupName("UpdatedName");
        groupDao.update(group, group.getGroupId());

        Group updatedGroup = groupDao.get(1).orElse(null);
        assertNotNull(updatedGroup);
        assertEquals("UpdatedName", updatedGroup.getGroupName());
    }

    @Test
    void delete() {
        groupDao.delete(1);

        List<Group> groups = groupDao.getAll();
        assertEquals(0,groups.size());
    }
}