ALTER TABLE student ADD CONSTRAINT age_CONSTRAINT CHECK (age > 16)

ALTER TABLE student ADD CONSTRAINT name_UNIQUE UNIQUE (name)

ALTER TABLE student ADD CONSTRAINT name_not_null CHECK (name is not null)

ALTER TABLE faculty ADD CONSTRAINT name_f_unique UNIQUE (name, color)

ALTER TABLE student ALTER COLUMN age SET DEFAULT 20


CREATE TABLE personal (
id INTEGER,
name TEXT PRIMARY KEY,
age INTEGER CHECK (age>0),
rights BOOLEAN NOT NULL DEFAULT false,
car_id TEXT REFERENCES car (id)
)

CREATE TABLE car (
id INTEGER PRIMARY KEY,
mark VARCHAR(100) UNIQUE not NUll,
model VARCHAR (100) UNIQUE NOT null,
prise INTEGER not null
)

select s.name, s.age, f.name
from student s, faculty f

select * from student s
where avatar_id is not null
