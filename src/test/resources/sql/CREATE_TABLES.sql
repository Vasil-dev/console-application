DROP TABLE IF EXISTS students_courses;


DROP TABLE IF EXISTS groups;

CREATE TABLE groups
(
    group_id INT,
    group_name VARCHAR(255)
);


DROP TABLE IF EXISTS students;

CREATE TABLE students
(
    student_id INT,
    group_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

DROP TABLE IF EXISTS courses;

CREATE TABLE courses
(
    course_id INT,
    course_name VARCHAR(255),
    course_description VARCHAR(255)
);
