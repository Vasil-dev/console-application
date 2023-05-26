package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import ua.foxminded.vasilmartsyniuk.consoleapp.dao.GroupDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.GroupService;

import java.util.List;
import java.util.Optional;

public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Optional<Group> get(int groupId) {
        return groupDao.get(groupId);
    }

    @Override
    public List<Group> getAll() {
        return groupDao.getAll();
    }

    @Override
    public void create(Group group) {
        groupDao.create(group);
    }

    @Override
    public void update(Group group, int groupId) {
        groupDao.update(group, groupId);
    }

    @Override
    public void delete(int groupId) {
        groupDao.delete(groupId);
    }
}
