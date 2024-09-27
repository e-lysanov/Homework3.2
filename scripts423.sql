-- имя-возраст студентов и их факультеты/отсутствие факультетов
SELECT student.name AS student_name, student.age AS student_age, faculty.name AS faculty_name
FROM student
         LEFT JOIN faculty ON student.faculty_id = faculty.id;

-- имена студентов с аватарками
SELECT student.name AS student_name, avatar.id AS avatar_id
FROM avatar
         INNER JOIN student ON avatar.student_id = student.id;