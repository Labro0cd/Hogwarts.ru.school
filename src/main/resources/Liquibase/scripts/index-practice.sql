-- liquibase formatted sql

-- changeset kKirill:1
CREATE INDEX student_name_index on student (name);

-- changeset kKirill:2
CREATE INDEX faculty_name_color_index on faculty (name, color);