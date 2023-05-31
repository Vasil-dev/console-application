package ua.foxminded.vasilmartsyniuk.consoleapp.servicetests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.GroupDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.GroupServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GroupServiceImplTest {

    @Mock
    private GroupDao groupDao;
    private GroupServiceImpl groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groupService = new GroupServiceImpl(groupDao);
    }

    @Test
    void testGetGroup() {
        int groupId = 1;
        Group group = new Group(groupId, "ER-21");
        when(groupDao.get(groupId)).thenReturn(Optional.of(group));

        Optional<Group> result = groupService.get(groupId);

        assertEquals(Optional.of(group), result);
        verify(groupDao).get(groupId);
    }

    @Test
    void testGetAllGroups() {
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1, "WE-43"));
        groups.add(new Group(2, "RT-32"));
        when(groupDao.getAll()).thenReturn(groups);

        List<Group> result = groupService.getAll();

        assertEquals(groups, result);
        verify(groupDao.getAll());
    }

    @Test
    void testCreateGroup() {
        Group group = new Group(1,"WE-23");
        groupService.create(group);
        verify(groupDao).create(group);
    }

    @Test
    void testUpdateGroup() {
        int groupID = 1;
        Group group = new Group(1,"ER-22");
        groupService.update(group,groupID);
        verify(groupDao).update(group,groupID);
    }

    @Test
    void testDeleteGroup() {
        int groupId = 1;
        Group group = new Group(1, "ER-22");
        groupService.delete(groupId);
        verify(groupDao).delete(groupId);
    }
}
