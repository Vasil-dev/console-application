DROP SCHEMA IF EXISTS cms CASCADE ;

DROP TABLE IF EXISTS cms.students_courses CASCADE;

DROP TABLE IF EXISTS cms.groups CASCADE ;

 CREATE SCHEMA cms
CREATE TABLE cms.groups
(
    group_id INT PRIMARY KEY,
    group_name VARCHAR(255)
);


DROP TABLE IF EXISTS cms.students CASCADE;

CREATE TABLE cms.students
(
    student_id INT PRIMARY KEY,
    group_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

DROP TABLE IF EXISTS cms.courses CASCADE;

CREATE TABLE cms.courses
(
    course_id INT PRIMARY KEY,
    course_name VARCHAR(255),
    course_description VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS cms.student_courses
(
    student_id INT,
    course_id  INT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES cms.students (student_id),
    FOREIGN KEY (course_id) REFERENCES cms.courses (course_id));
