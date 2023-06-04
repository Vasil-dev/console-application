package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StudentDao implements Dao<Student> {

    @PersistenceContext
    private EntityManager entityManager;


    public StudentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Student> get(int studentId) {
        try {
            String jpql = "SELECT s FROM Student s WHERE s.studentId = :studentId";
            Student student = entityManager.createQuery(jpql, Student.class)
                    .setParameter("studentId", studentId)
                    .getSingleResult();
            return Optional.ofNullable(student);
        } catch (
                NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getAll() {
        String jpql = "SELECT s FROM Student s";
        return entityManager.createQuery(jpql, Student.class).getResultList();
    }


//    @Override
//    public void create(Student student) {
//        try {
//            System.out.println("Persisting student: " + student);
//            entityManager.persist(student);
//
//        } catch (PersistenceException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void create(Student student) {
        try {
            Student newStudent = new Student();
            newStudent.setGroupId(student.getGroupId());
            newStudent.setFirstName(student.getFirstName());
            newStudent.setLastName(student.getLastName());

            entityManager.persist(newStudent);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void update(Student student, int studentId) {
        student.setStudentId(studentId);
        entityManager.merge(student);
    }

    @Override
    public void delete(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        if (student != null) {
            entityManager.remove(student);
        }
    }
}


