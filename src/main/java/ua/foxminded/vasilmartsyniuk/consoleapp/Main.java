package ua.foxminded.vasilmartsyniuk.consoleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.foxminded.vasilmartsyniuk.consoleapp.dao.CourseDao;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
}
