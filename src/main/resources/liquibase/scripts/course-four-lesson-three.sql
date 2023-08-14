-- liquibase formatted sql

-- changeset elysanov:1
CREATE INDEX student_name_index ON student (name);

-- changeset elysanov:2
CREATE INDEX faculty_nr_index ON faculty (name, color);

