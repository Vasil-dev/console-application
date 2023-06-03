package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDao implements Dao<Course> {

    @PersistenceContext
    private EntityManager entityManager;

    public CourseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Course> get(int courseId) {
        try {
            String jpql = "SELECT c FROM Course c WHERE c.courseId = :courseId";
            Course course = entityManager.createQuery(jpql, Course.class)
                    .setParameter("courseId", courseId)
                    .getSingleResult();
            return Optional.ofNullable(course);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> getAll() {
        String jpql = "SELECT c FROM Course c";
        return entityManager.createQuery(jpql, Course.class).getResultList();
    }


    @Override
    public void create(Course course) {
        try {
            entityManager.merge(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Course course, int courseId) {
       course.setCourseId(courseId);
       entityManager.merge(course);
    }

    @Override
    public void delete(int courseId) {
        Course course = entityManager.find(Course.class,courseId);
        if (course != null) {
            entityManager.remove(course);
        }
    }
}
