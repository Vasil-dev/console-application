package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupDao implements Dao<Group> {

    @PersistenceContext
    private EntityManager entityManager;

    public GroupDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Group> get(int groupId) {
        try {
            String jpql = "SELECT g FROM Group g WHERE g.groupId = :groupId";
            Group group = entityManager.createQuery(jpql, Group.class)
                    .setParameter("groupId", groupId)
                    .getSingleResult();
            return Optional.ofNullable(group);
        } catch (NoResultException e) {
            // Handle the exception
            return Optional.empty();
        }
    }

    @Override
    public List<Group> getAll() {
        String jpql = "SELECT g FROM Group g";
        return entityManager.createQuery(jpql, Group.class).getResultList();
    }


    @Override
    public void create(Group group) {
        try {
            entityManager.merge(group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Group group, int groupId) {
        group.setGroupId(groupId);
        entityManager.merge(group);
    }

    @Override
    public void delete(int groupId) {
        Group group = entityManager.find(Group.class, groupId);
        if (group != null) {
            entityManager.remove(group);
        }
    }
}
