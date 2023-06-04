package ua.foxminded.vasilmartsyniuk.consoleapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@Setter @Getter @ToString
public class Student  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    public Student() {
    }

    public Student(int studentId, int groupId, String firstName, String lastName) {
        this.studentId = studentId;
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

