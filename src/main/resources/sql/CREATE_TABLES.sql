CREATE TABLE IF NOT EXISTS groups
(
    group_id INT,
    group_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS students
(
    student_id INT,
    group_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS courses
(
    course_id INT,
    course_name VARCHAR(255),
    course_description VARCHAR(255)
);
