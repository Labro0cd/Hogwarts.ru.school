ALTER TABLE student ADD CONSTRAINT age_CONSTRAINT CHECK (age > 16)

ALTER TABLE student ADD CONSTRAINT name_UNIQUE UNIQUE (name)

ALTER TABLE student ADD CONSTRAINT name_not_null CHECK (name is not null)

ALTER TABLE faculty ADD CONSTRAINT name_f_unique UNIQUE (name, color)

ALTER TABLE student ALTER COLUMN age SET DEFAULT 20

select s.name, s.age, f.name
from student s, faculty f

select student.name, student.avatar_id, avatar.media_type
from student
INNER join avatar on student.avatar_id = avatar.id
