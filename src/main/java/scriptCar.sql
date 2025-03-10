CREATE TABLE personal (
personal_id INTEGER UNIQUE,
name TEXT PRIMARY KEY,
age INTEGER CHECK (age>0),
rights BOOLEAN NOT NULL DEFAULT FALSE,
car_id INTEGER REFERENCES car (id)
)

CREATE TABLE car (
id INTEGER,
mark VARCHAR(100) NOT NULL,
model VARCHAR (100) NOT NULL,
prise INTEGER NOT NULL,
personal_id INTEGER
)

ALTER TABLE car
ADD CONSTRAINT fk_personal
FOREIGN KEY (personal_id) REFERENCES personal(personal_id)





