package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.GroupRepository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.GroupService;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Optional<Group> get(int groupId) {
        logger.info("Getting group by Id: {}", groupId);
        return groupRepository.findById(groupId);
    }

    @Override
    public List<Group> getAll() {
        logger.info("Getting all groups: ");
        return groupRepository.findAll();
    }

    @Override
    public void create(Group group) {
        logger.info("Creating group: {}",group);
        groupRepository.save(group);
    }

    @Override
    public void update(Group group, int groupId) {
        logger.info("Updating group by Id: {}: {}",groupId,group);
        group.setGroupId(groupId);
        groupRepository.save(group);
    }

    @Override
    public void delete(int groupId) {
        logger.info("Deleting group by Id: {}",groupId);
        groupRepository.deleteById(groupId);
    }
}
