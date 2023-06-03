package ua.foxminded.vasilmartsyniuk.consoleapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Course;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Group;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.Student;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.CourseServiceImpl;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.GroupServiceImpl;
import ua.foxminded.vasilmartsyniuk.consoleapp.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final StudentServiceImpl studentService;
    private final GroupServiceImpl groupService;
    private final CourseServiceImpl courseService;


    public ApplicationRunner(StudentServiceImpl studentService, GroupServiceImpl groupService, CourseServiceImpl courseService) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.courseService = courseService;

    }


    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    getAll(scanner);
                }
                case 2 -> {
                    getById(scanner);
                }
                case 3 -> {
                    createObject(scanner);
                }
                case 4 -> {
                    deleteObject(scanner);
                }

                case 0 -> exit = true;
                default -> System.err.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }

        System.out.println("Exiting the application...");
    }

    private void displayMenu() {
        System.out.println("===== Console Menu =====");
        System.out.println("1. Get all...");
        System.out.println("2. Get by Id");
        System.out.println("3. Add new object");
        System.out.println("4. Delete object by Id");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void getAll(Scanner scanner) {
        System.out.println("Get all from:");
        System.out.println(" 1: Groups \n 2: Students \n 3: Courses");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                List<Group> groups = groupService.getAll();
                for (Group group : groups) {
                    System.out.println(group);
                }
            }
            case 2 -> {
                List<Student> students = studentService.getAll();
                for (Student student : students) {
                    System.out.println(student);
                }
            }
            case 3 -> {
                List<Course> courses = courseService.getAll();
                for (Course course : courses) {
                    System.out.println(course);
                }
            }
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }

    private void getById(Scanner scanner) {
        System.out.println("Get by Id from:");
        System.out.println(" 1: Groups \n 2: Students \n 3: Courses");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter group Id: ");
                int groupId = scanner.nextInt();
                Optional<Group> group = groupService.get(groupId);
                System.out.println(group);
            }
            case 2 -> {
                System.out.println("Enter student Id: ");
                int studentId = scanner.nextInt();
                Optional<Student> student = studentService.get(studentId);
                System.out.println(student);
            }
            case 3 -> {
                System.out.println("Enter course Id: ");
                int courseId = scanner.nextInt();
                Optional<Course> course = courseService.get(courseId);
                System.out.println(course);
            }
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }

    private void createObject(Scanner scanner) {
        System.out.println("Select object to add:");
        System.out.println(" 1: Group \n 2: Student \n 3: Course ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter group details");
                System.out.println("Group Id: ");
                int groupId = scanner.nextInt();

                System.out.println("Group name: ");
                String groupName = scanner.nextLine();
                groupName = scanner.nextLine();

                Group group = new Group(groupId, groupName);
                groupService.create(group);

                System.out.println("Group added");
            }
            case 2 -> {
                System.out.println("Enter student details");
                System.out.println("Student Id: ");
                int studentId = scanner.nextInt();

                System.out.println("Group Id: ");
                int groupId = scanner.nextInt();

                System.out.println("First name: ");
                String firstName = scanner.nextLine();
                firstName = scanner.nextLine();

                System.out.println("Last name: ");
                String lastName = scanner.nextLine();


                Student student = new Student(studentId, groupId, firstName, lastName);
                studentService.create(student);

                System.out.println("Student added");

            }
            case 3 -> {
                System.out.println("Enter course details");
                System.out.println("Course Id: ");
                int courseId = scanner.nextInt();

                System.out.println("Course name: ");
                String courseName = scanner.nextLine();

                Course course = new Course(courseId, courseName, null);
                courseService.create(course);

                System.out.println("Course added");
            }
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }

    private void deleteObject(Scanner scanner) {
        System.out.println("Select object to delete");
        System.out.println(" 1: Group \n 2: Student \n 3: Course ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter group Id: ");
                int groupId = scanner.nextInt();
                groupService.delete(groupId);
                System.out.println("Group deleted");
            }
            case 2 -> {
                System.out.println("Enter student Id: ");
                int studentId = scanner.nextInt();
                studentService.delete(studentId);
                System.out.println("Student deleted");

            }
            case 3 -> {
                System.out.println("Enter course Id: ");
                int courseId = scanner.nextInt();
                courseService.delete(courseId);
                System.out.println("Course deleted");
            }
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }
}
