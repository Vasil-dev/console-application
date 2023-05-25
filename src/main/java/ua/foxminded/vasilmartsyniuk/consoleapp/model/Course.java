package ua.foxminded.vasilmartsyniuk.consoleapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Course {
    private int courseId;
    private String courseName;
    private String courseDescription;


    public Course(int courseId, String courseName, String courseDescription) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }
}
