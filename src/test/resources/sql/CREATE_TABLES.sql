DROP TABLE IF EXISTS students_courses CASCADE;

DROP TABLE IF EXISTS cms.groups CASCADE ;

 CREATE SCHEMA cms
CREATE TABLE cms.groups
(
    group_id INT,
    group_name VARCHAR(255)
);


DROP TABLE IF EXISTS cms.students CASCADE;

CREATE TABLE cms.students
(
    student_id INT,
    group_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

DROP TABLE IF EXISTS cms.courses CASCADE;

CREATE TABLE cms.courses
(
    course_id INT,
    course_name VARCHAR(255),
    course_description VARCHAR(255)
);
