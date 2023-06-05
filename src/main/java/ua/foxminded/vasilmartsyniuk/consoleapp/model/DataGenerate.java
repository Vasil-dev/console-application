package ua.foxminded.vasilmartsyniuk.consoleapp.model;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataGenerate implements ApplicationRunner {

    private static final String[] FIRST_NAMES = {
            "Vasil", "Volodimir", "Mihail", "Sophia", "Victor", "Olena", "Ivan", "Olha", "Olexander", "Roman",
            "Anna", "Natalia", "Eva", "Andrew", "Mia", "Stepan", "Isabella", "Petr", "Yulia", "Inokentiy"
    };

    private static final String[] LAST_NAMES = {
            "Martsyniuk", "Olesko", "Petriga", "Oberemko", "Bediuk", "Mamchur", "Adamchuk", "Boiko", "Boda", "Pastushin",
            "Matviychuk", "Olasiuk", "Zabavskiy", "Fedikiv", "Kulic", "Fedun", "Smoliarchuk", "Vorobchuk", "Yarockiy", "Budanov"
    };

    private static final String[] COURSES = {
            "Math", "Biology", "Physics", "Chemistry", "History", "English", "Computer Science", "Geography", "Art", "Music"
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataGenerate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (isDatabaseEmpty()) {
            executeDataGeneration();
        } else {
            System.out.println("Database is not empty.");
        }
    }

    public void executeDataGeneration() {
        generateGroups(10);
        generateCourses(10);
        generateAndInsertStudents(200);
        assignStudentsToGroups();
        createStudentCourseRelations();
    }

    private void generateGroups(int numGroups) {
        for (int i = 1; i <= numGroups; i++) {
            String groupName = generateGroupName();
            final String GENERATE_GROUPS = "INSERT INTO cms.groups (group_id, group_name) VALUES (?, ?)";
            jdbcTemplate.update(GENERATE_GROUPS, i, groupName);
        }
    }

    private String generateGroupName() {
        Random random = new Random();
        int firstNumber = random.nextInt(10);
        int secondNumber = random.nextInt(10);
        return String.format("%c%c-%d%d", generateSymbols(), generateSymbols(), firstNumber, secondNumber);
    }

    private char generateSymbols() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'A');
    }

    private void generateCourses(int numCourse) {
        for (int i = 1; i <= numCourse; i++) {
            String courseName = COURSES[i - 1];
            final String GENERATE_COURSES = "INSERT INTO cms.courses (course_id, course_name) VALUES (?, ?)";
            jdbcTemplate.update(GENERATE_COURSES, i, courseName);
        }
    }

    private void generateAndInsertStudents(int numStudents) {
        for (int i = 1; i <= numStudents; i++) {
            String firstName = getFirstName();
            String lastName = getLastName();
            final String GENERATE_AND_INSERT_STUDENTS = "INSERT INTO cms.students (student_id, first_name, last_name) VALUES (?, ?, ?)";
            jdbcTemplate.update(GENERATE_AND_INSERT_STUDENTS, i, firstName, lastName);
        }
    }

    private String getFirstName() {
        Random random = new Random();
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    private String getLastName() {
        Random random = new Random();
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    private void assignStudentsToGroups() {
        Random random = new Random();

        val GET_GROUP_BY_ID = "SELECT group_id FROM cms.groups";
        List<Integer> groupId = jdbcTemplate.queryForList(GET_GROUP_BY_ID, Integer.class);

        val GET_STUDENT_BY_ID = "SELECT student_id FROM cms.students";
        List<Integer> studentId = jdbcTemplate.queryForList(GET_STUDENT_BY_ID, Integer.class);

        for (int students : studentId) {
            int groups = groupId.get(random.nextInt(groupId.size()));
            final String ASSIGN_STUDENTS_TO_GROUPS = "UPDATE cms.students SET group_id = ? WHERE student_id = ?";
            jdbcTemplate.update(ASSIGN_STUDENTS_TO_GROUPS, groups, students);
        }
        System.out.println("Students assigned to groups successfully.");
    }

    private void createStudentCourseRelations() {
        Random random = new Random();

        val GET_COURSES = "SELECT course_id FROM cms.courses";
        var courseId = jdbcTemplate.queryForList(GET_COURSES, Integer.class);

        val GET_STUDENTS = "SELECT student_id FROM cms.students";
        var studentId = jdbcTemplate.queryForList(GET_STUDENTS,Integer.class);

        for (int students : studentId) {
            int numCourse = random.nextInt(3) + 1;
            List<Integer> assignedCourseId = new ArrayList<>();
            for (int i = 0; i < numCourse; i++) {
                int courses = courseId.get(random.nextInt(courseId.size()));
                if (!assignedCourseId.contains(courses)) {
                    assignedCourseId.add(courses);

                    String assignStudentsToCourses = "INSERT INTO cms.student_courses (student_id, course_id) VALUES (?, ?)";
                    jdbcTemplate.update(assignStudentsToCourses, students, courses);
                }
            }
        }
    }

    private boolean isDatabaseEmpty() {
        String countQuery = "SELECT COUNT(*) FROM cms.students";
        Integer count = jdbcTemplate.queryForObject(countQuery, Integer.class);
        return count != null && count == 0;
    }
}
