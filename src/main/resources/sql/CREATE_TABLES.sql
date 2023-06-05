CREATE SCHEMA IF NOT EXISTS cms;

CREATE TABLE IF NOT EXISTS cms.groups
(
    group_id INT,
    group_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cms.students
(
    student_id INT,
    group_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cms.courses
(
    course_id INT,
    course_name VARCHAR(255),
    course_description VARCHAR(255)
);
