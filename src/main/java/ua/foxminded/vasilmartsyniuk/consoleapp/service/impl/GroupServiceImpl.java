package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.GroupDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.GroupService;

import java.util.List;
import java.util.Optional;
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Optional<Group> get(int groupId) {
        logger.info("Getting group by Id: {}", groupId);
        return groupDao.get(groupId);
    }

    @Override
    public List<Group> getAll() {
        logger.info("Getting all groups: ");
        return groupDao.getAll();
    }

    @Override
    public void create(Group group) {
        logger.info("Creating group: {}",group);
        groupDao.create(group);
    }

    @Override
    public void update(Group group, int groupId) {
        logger.info("Updating group by Id: {}: {}",groupId,group);
        groupDao.update(group, groupId);
    }

    @Override
    public void delete(int groupId) {
        logger.info("Deleting group by Id: {}",groupId);
        groupDao.delete(groupId);
    }
}
