package ua.foxminded.vasilmartsyniuk.consoleapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
}

