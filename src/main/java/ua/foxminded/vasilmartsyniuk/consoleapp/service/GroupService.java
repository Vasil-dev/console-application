package ua.foxminded.vasilmartsyniuk.consoleapp.service;

import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Optional<Group> get(int groupId);

    List<Group> getAll();

    void create(Group group);

    void update(Group group,int groupId);

    void delete(int groupId);
}
